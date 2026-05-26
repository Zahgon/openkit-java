/**
 * Copyright 2018-2021 Dynatrace LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dynatrace.openkit.core.caching;

import com.dynatrace.openkit.api.Logger;
import com.dynatrace.openkit.protocol.Beacon;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Class used in OpenKit to cache serialized {@link Beacon} data.
 *
 * <p>
 * This cache needs to deal with high concurrency, since it's possible that a lot of threads
 * insert new data concurrently.
 *
 * Furthermore two OpenKit internal threads are also accessing the cache.
 * </p>
 */
public class BeaconCacheImpl extends Observable implements BeaconCache {

    private final Logger logger;

    private final ReadWriteLock globalCacheLock;

    private final Map<BeaconKey, BeaconCacheEntry> beacons;

    private final AtomicLong cacheSizeInBytes;

    /**
     * Create BeaconCache.
     *
     * @param logger For trace messages.
     */
    public BeaconCacheImpl(Logger logger) {
        this.logger = logger;
        globalCacheLock = new ReentrantReadWriteLock();
        beacons = new HashMap<>();
        cacheSizeInBytes = new AtomicLong(0L);
    }

    @Override
    public void addEventData(BeaconKey key, long timestamp, String data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void addActionData(BeaconKey key, long timestamp, String data) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void deleteCacheEntry(BeaconKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void prepareDataForSending(BeaconKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean hasDataForSending(BeaconKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getNextBeaconChunk(BeaconKey key, String chunkPrefix, int maxSize, char delimiter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void removeChunkedData(BeaconKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void resetChunkedData(BeaconKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get cached {@link BeaconCacheEntry} or insert new one if nothing exists for given {@code key}.
     *
     * @param key The key of the beacon to search for.
     *
     * @return The already cached entry or newly created one.
     */
    private BeaconCacheEntry getCachedEntryOrInsert(BeaconKey key) {
        // get the appropriate cache entry
        BeaconCacheEntry entry = getCachedEntry(key);
        if (entry == null) {
            try {
                // does not exist, and needs to be inserted
                globalCacheLock.writeLock().lock();
                if (!beacons.containsKey(key)) {
                    // double check since this could have been added in the mean time
                    entry = new BeaconCacheEntry();
                    beacons.put(key, entry);
                } else {
                    entry = beacons.get(key);
                }
            } finally {
                globalCacheLock.writeLock().unlock();
            }
        }
        return entry;
    }

    /**
     * Get a shallow copy of events collected so far.
     *
     * <p>
     * Although this method is intended for debugging purposes only, it still does appropriate locking.
     * </p>
     *
     * @param key The key of the beacon for which to retrieve the events.
     *
     * @return List of event data.
     */
    public String[] getEvents(BeaconKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a shallow copy of events that are about to be sent.
     *
     * <P>
     * This method is only intended for internal unit tests.
     * </P>
     *
     * @param key The key of the beacon for which to retrieve the events.
     *
     * @return List of event data.
     */
    List<BeaconCacheRecord> getEventsBeingSent(BeaconKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a shallow copy of actions collected so far.
     *
     * <p>
     * Although this method is intended for debugging purposes only, it still does appropriate locking.
     * </p>
     *
     * @param key The key of the beacon for which to retrieve the events.
     *
     * @return List of event data.
     */
    public String[] getActions(BeaconKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a shallow copy of events that are about to be sent.
     *
     * <P>
     * This method is only intended for internal unit tests.
     * </P>
     *
     * @param key The key of the beacon for which to retrieve the events.
     *
     * @return List of event data.
     */
    List<BeaconCacheRecord> getActionsBeingSent(BeaconKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static String[] extractData(List<BeaconCacheRecord> eventData) {
        List<String> result = new ArrayList<>(eventData.size());
        for (BeaconCacheRecord record : eventData) {
            result.add(record.getData());
        }
        return result.toArray(new String[0]);
    }

    /**
     * Get cached {@link BeaconCacheEntry} or {@code null} if nothing exists for given {@code key}.
     *
     * @param key The key of the beacon to search for.
     *
     * @return The cached entry or {@code null}.
     */
    private BeaconCacheEntry getCachedEntry(BeaconKey key) {
        BeaconCacheEntry entry;
        // acquire read lock and get the entry
        try {
            globalCacheLock.readLock().lock();
            entry = beacons.get(key);
        } finally {
            globalCacheLock.readLock().unlock();
        }
        return entry;
    }

    @Override
    public Set<BeaconKey> getBeaconKeys() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int evictRecordsByAge(BeaconKey key, long minTimestamp) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int evictRecordsByNumber(BeaconKey key, int numRecords) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getNumBytesInCache() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Call this method when something was added (size of cache increased).
     */
    private void onDataAdded() {
        setChanged();
        notifyObservers();
    }

    @Override
    public boolean isEmpty(BeaconKey key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
