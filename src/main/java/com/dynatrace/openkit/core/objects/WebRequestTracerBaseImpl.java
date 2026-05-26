/**
 * Copyright 2018-2021 Dynatrace LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dynatrace.openkit.core.objects;

import com.dynatrace.openkit.api.Logger;
import com.dynatrace.openkit.api.WebRequestTracer;
import com.dynatrace.openkit.protocol.Beacon;

/**
 * Abstract base class implementation of the {@link WebRequestTracer} interface.
 *
 * <p>
 *     This class is guaranteed to be thread safe.
 * </p>
 */
public abstract class WebRequestTracerBaseImpl implements WebRequestTracer, CancelableOpenKitObject {

    static final String UNKNOWN_URL = "<unknown>";

    /**
     * {@link Logger} for tracing log message
     */
    private final Logger logger;

    /**
     * Parent object of this web request tracer
     */
    private OpenKitComposite parent;

    /**
     * object for synchronization
     */
    private final Object lockObject = new Object();

    /**
     * Dynatrace tag that has to be used for tracing the web request
     */
    private final String tag;

    /**
     * Beacon for sending data
     */
    private final Beacon beacon;

    /**
     * The parent action id
     */
    private final int parentActionID;

    /**
     * URL to trace (excluding query args)
     */
    private final String url;

    /**
     * The response code received from the request
     */
    private int responseCode = -1;

    /**
     * The number of bytes sent
     */
    private long bytesSent = -1;

    /**
     * The number of bytes received
     */
    private long bytesReceived = -1;

    /**
     * Start time of the web request, set in {@link #start()}
     */
    private long startTime;

    /**
     * End time of the web request, set in {@link #stop(int)}
     */
    private long endTime = -1;

    /**
     * starting sequence number
     */
    private final int startSequenceNo;

    /**
     * ending sequence number
     */
    private int endSequenceNo = -1;

    /**
     * Constructor.
     *
     * @param logger The logger used to log information
     * @param parent The parent object, to which this web request tracer belongs to
     * @param url The URL to trace
     * @param beacon {@link Beacon} for data sending and tag creation
     */
    WebRequestTracerBaseImpl(Logger logger, OpenKitComposite parent, String url, Beacon beacon) {
        this.logger = logger;
        this.parent = parent;
        this.url = url;
        this.beacon = beacon;
        parentActionID = parent.getActionID();
        // creating start sequence number has to be done here, because it's needed for the creation of the tag
        startSequenceNo = beacon.createSequenceNumber();
        tag = beacon.createTag(parentActionID, startSequenceNo);
        // if start is not called before using the setters the start time (e.g. load time) is not in 1970
        startTime = beacon.getCurrentTimestamp();
    }

    @Override
    public String getTag() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public WebRequestTracer setBytesSent(int bytesSent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public WebRequestTracer setBytesSent(long bytesSent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public WebRequestTracer setBytesReceived(int bytesReceived) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public WebRequestTracer setBytesReceived(long bytesReceived) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public WebRequestTracer start() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void stop(int responseCode) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void doStop(int responseCode, boolean discardData) {
        synchronized (lockObject) {
            if (isStopped()) {
                // stop has been called previously
                return;
            }
            this.responseCode = responseCode;
            endSequenceNo = beacon.createSequenceNumber();
            endTime = beacon.getCurrentTimestamp();
        }
        // add web request to beacon
        if (!discardData) {
            beacon.addWebRequest(parentActionID, this);
        }
        // last but not least notify the parent & detach from parent
        parent.onChildClosed(this);
        parent = null;
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getURL() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getResponseCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public long getStartTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public long getEndTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getStartSequenceNo() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getEndSequenceNo() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public long getBytesSent() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public long getBytesReceived() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isStopped() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    OpenKitComposite getParent() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
