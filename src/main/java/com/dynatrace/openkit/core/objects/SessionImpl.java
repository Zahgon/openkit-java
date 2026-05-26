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
package com.dynatrace.openkit.core.objects;

import com.dynatrace.openkit.api.ConnectionType;
import com.dynatrace.openkit.api.Logger;
import com.dynatrace.openkit.api.RootAction;
import com.dynatrace.openkit.api.Session;
import com.dynatrace.openkit.api.WebRequestTracer;
import com.dynatrace.openkit.core.configuration.ServerConfiguration;
import com.dynatrace.openkit.protocol.AdditionalQueryParameters;
import com.dynatrace.openkit.protocol.Beacon;
import com.dynatrace.openkit.protocol.StatusResponse;
import com.dynatrace.openkit.providers.HTTPClientProvider;
import com.dynatrace.openkit.util.json.objects.JSONValue;
import java.io.IOException;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Actual implementation of the {@link Session} interface.
 */
public class SessionImpl extends OpenKitComposite implements Session {

    /**
     * The maximum number of "new session requests" to send per session.
     */
    public static final int MAX_NEW_SESSION_REQUESTS = 4;

    /**
     * {@link Logger} for tracing log message
     */
    private final Logger logger;

    /**
     * Parent object of this {@link Session}
     */
    private OpenKitComposite parent;

    /**
     * Beacon reference
     */
    private final Beacon beacon;

    /**
     * current state of the session (also used for synchronization
     */
    private final SessionStateImpl state;

    /**
     * the number of tries for new session requests
     */
    private int numRemainingNewSessionRequests = MAX_NEW_SESSION_REQUESTS;

    /**
     * the time when the session is to be ended (including a grace period from when the session was split by events)
     */
    private final AtomicLong splitByEventsGracePeriodEndTimeInMillis = new AtomicLong(-1);

    /**
     * Container for additional mutable basic data which can be set via session
     */
    private final SupplementaryBasicData supplementaryBasicData;

    SessionImpl(Logger logger, OpenKitComposite parent, Beacon beacon, SupplementaryBasicData supplementaryBasicData) {
        this.state = new SessionStateImpl(this);
        this.logger = logger;
        this.parent = parent;
        this.beacon = beacon;
        this.supplementaryBasicData = supplementaryBasicData;
        beacon.startSession();
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public RootAction enterAction(String actionName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void identifyUser(String userTag) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void reportCrash(String errorName, String reason, String stacktrace) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void reportCrash(Throwable throwable) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void reportNetworkTechnology(String technology) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void reportConnectionType(ConnectionType connectionType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void reportCarrier(String carrier) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public WebRequestTracer traceWebRequest(URLConnection connection) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public WebRequestTracer traceWebRequest(String url) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void sendBizEvent(String type, Map<String, JSONValue> attributes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    void sendEvent(String name, Map<String, JSONValue> attributes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void end() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void end(boolean sendSessionEndEvent) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Tries to end the current session by checking if there are no more child objects (actions / web request tracers)
     * open. In case no more child objects are open the session is ended otherwise it is kept open.
     *
     * @return {@code true} if the session was successfully ended (or was already ended before). {@code false} in case
     *  there are / were still open child objects (actions / web request tracers).
     */
    public boolean tryEnd() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the end time when the session is to be actually ended after a session split by event count. It might
     * occur that a session is not yet ready to be finished (e.g. open actions, tracers) when the session split happens.
     * In this case the session is kept open and closed at a later time. The given end time is the point in time at
     * which the session will be closed forcefully by the {@link com.dynatrace.openkit.core.SessionWatchdog} thread.
     *
     * @param endTime the time when the session is to be closed for good.
     */
    public void setSplitByEventsGracePeriodEndTimeInMillis(long endTime) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the time when the session is to be ended (after it was not possible to end the session after splitting
     * events e.g. due to actions still being open). The returned time already includes a grace period.
     */
    public long getSplitByEventsGracePeriodEndTimeInMillis() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sends the current beacon state.
     *
     * @param clientProvider Provider class providing the client for data transmission.
     * @param additionalParameters additional parameters that will be appended to the beacon request (can be {@code null}).
     *
     * @return Response from client.
     */
    public StatusResponse sendBeacon(HTTPClientProvider clientProvider, AdditionalQueryParameters additionalParameters) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Clears data that has been captured so far.
     *
     * <p>
     * This is called, when capturing is turned off to avoid having too much data.
     * </p>
     */
    public void clearCapturedData() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Test if this Session is empty or not.
     *
     * <p>
     * A session is considered to be empty, if it does not contain any action or event data.
     * </p>
     *
     * @return {@code true} if the session is empty, {@code false} otherwise.
     */
    public boolean isEmpty() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Initializes the {@link Beacon} with the given {@link ServerConfiguration}
     */
    public void initializeServerConfiguration(ServerConfiguration initialServerConfig) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Update the {@link Beacon} with the given {@link ServerConfiguration}
     */
    public void updateServerConfiguration(ServerConfiguration serverConfiguration) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public SessionState getState() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void onChildClosed(OpenKitObject childObject) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Indicates whether sending data for this session is allowed or not.
     */
    public boolean isDataSendingAllowed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Enables capturing for this session.
     *
     * <p>
     *     Will implicitly also set the {@link #getState() session state} to {@link SessionState#isConfigured() configured}.
     * </p>
     */
    public void enableCapture() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Disables capturing for this session.
     *
     * <p>
     *     Will implicitly also set the {@link #getState() session state} to {@link SessionState#isConfigured() configured}.
     * </p>
     */
    public void disableCapture() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Indicates whether new session requests can be sent or not.
     *
     * <p>
     *     This is directly related to {@link #decreaseNumRemainingSessionRequests()}.
     * </p>
     */
    public boolean canSendNewSessionRequest() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Decreases the number of remaining new session requests.
     *
     * <p>
     *     In case no more new session requests remain, {@link #canSendNewSessionRequest()} will return {@code false}
     * </p>
     */
    public void decreaseNumRemainingSessionRequests() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the beacon of this session.
     */
    Beacon getBeacon() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Implements the internal state of the {@link Session}
     */
    private static class SessionStateImpl implements SessionState {

        private final SessionImpl session;

        private boolean isFinishing = false;

        private boolean isFinished = false;

        private boolean wasTriedForEnding = false;

        private SessionStateImpl(SessionImpl session) {
            this.session = session;
        }

        @Override
        public synchronized boolean wasTriedForEnding() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized boolean isConfigured() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized boolean isConfiguredAndFinished() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized boolean isConfiguredAndOpen() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public synchronized boolean isFinished() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private synchronized boolean isFinishingOrFinished() {
            return isFinishing || isFinished;
        }

        private synchronized boolean markAsIsFinishing() {
            if (isFinishingOrFinished()) {
                return false;
            }
            isFinishing = true;
            return true;
        }

        private synchronized void markAsFinished() {
            isFinished = true;
        }

        private synchronized void markAsWasTriedForEnding() {
            wasTriedForEnding = true;
        }
    }
}
