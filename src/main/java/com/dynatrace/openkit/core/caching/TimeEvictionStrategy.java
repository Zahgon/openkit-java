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
import com.dynatrace.openkit.core.configuration.BeaconCacheConfiguration;
import com.dynatrace.openkit.providers.TimingProvider;
import java.util.Iterator;
import java.util.Set;

/**
 * Time based eviction strategy for the beacon cache.
 *
 * <p>
 * This strategy deletes all records from {@link BeaconCache} exceeding a certain age.
 * </p>
 */
class TimeEvictionStrategy implements BeaconCacheEvictionStrategy {

    private final Logger logger;

    private final BeaconCache beaconCache;

    private final BeaconCacheConfiguration configuration;

    private final TimingProvider timingProvider;

    private long lastRunTimestamp = -1;

    private boolean infoShown = false;

    /**
     * Constructor.
     *
     * @param logger Instance implementing the {@link Logger} interface for writing some useful debug messages.
     * @param beaconCache The beacon cache to evict if necessary.
     * @param configuration The configuration providing the boundary settings for this strategy.
     */
    TimeEvictionStrategy(Logger logger, BeaconCache beaconCache, BeaconCacheConfiguration configuration, TimingProvider timingProvider) {
        this.logger = logger;
        this.beaconCache = beaconCache;
        this.configuration = configuration;
        this.timingProvider = timingProvider;
    }

    @Override
    public void execute() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks if the strategy is disabled.
     *
     * <p>
     * The strategy might be disabled on purpose, if the maximum record age is less than or equal to zero.
     * </p>
     *
     * @return {@code true} if strategy is disabled, {@code false} otherwise.
     */
    boolean isStrategyDisabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a boolean flag indicating whether the strategy shall be executed ({@link #doExecute()} is executed) or not.
     *
     * @return {@code true} if the strategy shall be executed, {@code false} otherwise.
     */
    boolean shouldRun() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the timestamp when this strategy was executed last.
     *
     * @return A timestamp (the number of milliseconds elapsed, since 1970-01-01) when this strategy was last time executed.
     */
    long getLastRunTimestamp() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set the timestamp when this strategy was executed last.
     *
     * @param lastRunTimestamp A timestamp (the number of milliseconds elapsed, since 1970-01-01)
     *                         when this strategy was last time executed.
     */
    void setLastRunTimestamp(long lastRunTimestamp) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Real strategy execution.
     */
    private void doExecute() {
        // first get a snapshot of all inserted beacons
        Set<BeaconKey> beaconKeys = beaconCache.getBeaconKeys();
        if (beaconKeys.isEmpty()) {
            // no beacons - set last run timestamp and return immediately
            setLastRunTimestamp(timingProvider.provideTimestampInMilliseconds());
            return;
        }
        // retrieve the timestamp when we start with execution
        long currentTimestamp = timingProvider.provideTimestampInMilliseconds();
        long smallestAllowedBeaconTimestamp = currentTimestamp - configuration.getMaxRecordAge();
        // iterate over the previously obtained set and evict for each beacon
        Iterator<BeaconKey> beaconKeyIterator = beaconKeys.iterator();
        while (!Thread.currentThread().isInterrupted() && beaconKeyIterator.hasNext()) {
            BeaconKey beaconKey = beaconKeyIterator.next();
            int numRecordsRemoved = beaconCache.evictRecordsByAge(beaconKey, smallestAllowedBeaconTimestamp);
            if (numRecordsRemoved > 0 && logger.isDebugEnabled()) {
                logger.debug(getClass().getSimpleName() + " doExecute() - Removed " + numRecordsRemoved + " records from Beacon with key " + beaconKey);
            }
        }
        // last but not least update the last runtime
        setLastRunTimestamp(currentTimestamp);
    }
}
