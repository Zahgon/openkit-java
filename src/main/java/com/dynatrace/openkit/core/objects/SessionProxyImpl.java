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
import com.dynatrace.openkit.core.BeaconSender;
import com.dynatrace.openkit.core.SessionWatchdog;
import com.dynatrace.openkit.core.configuration.ServerConfiguration;
import com.dynatrace.openkit.core.configuration.ServerConfigurationUpdateCallback;
import com.dynatrace.openkit.protocol.Beacon;
import com.dynatrace.openkit.providers.TimingProvider;
import com.dynatrace.openkit.util.json.objects.JSONValue;
import java.io.IOException;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implements a surrogate for a {@link Session} to perform session splitting after:
 * <ul>
 *     <li>a configured number of events</li>
 *     <li>after a configured idle timeout</li>
 *     <li>after a configured maximum session duration</li>
 * </ul>
 */
public class SessionProxyImpl extends OpenKitComposite implements Session, ServerConfigurationUpdateCallback {

    // object used for synchronization.
    private final Object lockObject = new Object();

    // log message reporter
    private final Logger logger;

    // Parent object of this session proxy
    private final OpenKitComposite parent;

    // creator for split sessions
    private final SessionCreator sessionCreator;

    // provider to obtain the current time
    private final TimingProvider timingProvider;

    // sender of beacon data
    private final BeaconSender beaconSender;

    // watchdog to split sessions after idle/max timeout or to close split off sessions which were not closable on split
    private final SessionWatchdog sessionWatchdog;

    // the current session instance
    private SessionImpl currentSession;

    // holds the number of received calls to enterAction
    private int topLevelActionCount = 0;

    // specifies the timestamp when the last top level event happened
    private long lastInteractionTime;

    // the server configuration of the first session (will be initialized when first session is updated with server config)
    private ServerConfiguration serverConfiguration;

    // indicates if this session proxy was already finished
    private boolean isFinished;

    // last user tag reported via identifyUser
    private String lastUserTag = null;

    SessionProxyImpl(Logger logger, OpenKitComposite parent, SessionCreator sessionCreator, TimingProvider timingProvider, BeaconSender beaconSender, SessionWatchdog sessionWatchdog) {
        this.logger = logger;
        this.parent = parent;
        this.sessionCreator = sessionCreator;
        this.timingProvider = timingProvider;
        this.beaconSender = beaconSender;
        this.sessionWatchdog = sessionWatchdog;
        ServerConfiguration currentServerConfig = beaconSender.getLastServerConfiguration();
        createInitialSessionAndMakeCurrent(currentServerConfig);
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

    /**
     * Close all child objects of this {@link SessionProxyImpl} which are still open.
     */
    void closeChildObjects() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Close single child object.
     *
     * @param childObject Child object to close.
     */
    private void closeChildObject(OpenKitObject childObject) {
        try {
            childObject.close();
        } catch (IOException e) {
            // should not happen, nevertheless let's log an error
            logger.error(this + "Caught IOException while closing OpenKitObject (" + childObject + ")", e);
        }
    }

    /**
     * Indicates whether this session proxy was finished or is still open.
     */
    public boolean isFinished() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void onChildClosed(OpenKitObject childObject) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the number of top level action calls which were made to the current session. Intended to be used by unit
     * tests only.
     */
    int getTopLevelActionCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the time when the last top level event was called. Intended to be used by unit tests only.
     */
    long getLastInteractionTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the server configuration of this session proxy. Intended to be used by unit tests only.
     */
    ServerConfiguration getServerConfiguration() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the current active session or creates a new session if {@link #isSessionSplitByEventsRequired()}.
     */
    private SessionImpl getOrSplitCurrentSessionByEvents() {
        if (isSessionSplitByEventsRequired()) {
            closeOrEnqueueCurrentSessionForClosing();
            createSplitSessionAndMakeCurrent(serverConfiguration);
            reTagCurrentSession();
        }
        return currentSession;
    }

    /**
     * Indicates if the maximum number of top level actions is reached and session splitting by events needs to be
     * performed.
     */
    private boolean isSessionSplitByEventsRequired() {
        if (serverConfiguration == null || !serverConfiguration.isSessionSplitByEventsEnabled()) {
            return false;
        }
        return serverConfiguration.getMaxEventsPerSession() <= topLevelActionCount;
    }

    /**
     * Will end the current active session, enque the old one for closing, and create a new session.
     *
     * <p>
     * The new session is created using the {@see #createInitialSession}.
     * </p>
     *
     * <p>
     * This method must be called only when the {@link #lockObject} is held.
     * </p>
     */
    private void splitAndCreateNewInitialSession() {
        closeOrEnqueueCurrentSessionForClosing();
        // create a completely new SessionImpl
        sessionCreator.reset();
        createInitialSessionAndMakeCurrent(serverConfiguration);
        reTagCurrentSession();
    }

    private void closeOrEnqueueCurrentSessionForClosing() {
        // for grace period use half of the idle timeout
        // or fallback to session interval if not configured
        int closeGracePeriodInMillis = serverConfiguration.getSessionTimeoutInMilliseconds() > 0 ? serverConfiguration.getSessionTimeoutInMilliseconds() / 2 : serverConfiguration.getSendIntervalInMilliseconds();
        sessionWatchdog.closeOrEnqueueForClosing(currentSession, closeGracePeriodInMillis);
    }

    /**
     * Will end the current active session and start a new one but only if the following conditions are met:
     * <ul>
     *     <li>this session proxy is not {@link #isFinished() finished}.</li>
     *     <li>
     *          session splitting by idle timeout is enabled and the current session was idle for longer than the
     *          configured timeout.
     *     </li>
     *     <li>
     *         session splitting by maximum session duration is enabled and the session was open for longer than the
     *         maximum configured session duration.
     *     </li>
     * </ul>
     *
     * @return the time when the session might be split next. This can either be the time when the maximum session
     * duration is reached or the time when the idle timeout expires. In case this session proxy is finished, {@code -1}
     * is returned.
     */
    public long splitSessionByTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Calculates and returns the next point in time when this session is to be split. The returned time might either be
     * <ul>
     *     <li>the time when the session expires after the max. session duration elapsed.</li>
     *     <li>the time when the session expires after being idle.</li>
     * </ul>
     * depending on which happens earlier.
     */
    private long calculateNextSplitTime() {
        if (serverConfiguration == null) {
            return -1;
        }
        boolean splitByIdleTimeout = serverConfiguration.isSessionSplitByIdleTimeoutEnabled();
        boolean splitBySessionDuration = serverConfiguration.isSessionSplitBySessionDurationEnabled();
        long idleTimeOut = lastInteractionTime + serverConfiguration.getSessionTimeoutInMilliseconds();
        long sessionMaxTime = currentSession.getBeacon().getSessionStartTime() + serverConfiguration.getMaxSessionDurationInMilliseconds();
        if (splitByIdleTimeout && splitBySessionDuration) {
            return Math.min(idleTimeOut, sessionMaxTime);
        } else if (splitByIdleTimeout) {
            return idleTimeOut;
        } else if (splitBySessionDuration) {
            return sessionMaxTime;
        }
        return -1;
    }

    private void createInitialSessionAndMakeCurrent(ServerConfiguration initialServerConfig) {
        createAndAssignCurrentSession(initialServerConfig, null);
    }

    private void createSplitSessionAndMakeCurrent(ServerConfiguration updatedServerConfig) {
        createAndAssignCurrentSession(null, updatedServerConfig);
    }

    /**
     * Creates a new session and adds it to the beacon sender. The top level action count is reset to zero and the last
     * interaction time is set to the current timestamp.
     *
     * <p>
     * In case the given {@code initialServerConfig} is not null, the new session will be initialized with this server
     * configuration. The created session however will not be in state {@link SessionState#isConfigured() configured},
     * meaning new session requests will be performed for this session.
     * </p>
     * <p>
     * In case the given {@code updatedServerConfig} is not null, the new session will be updated with this server
     * configuration. The created session will be in state {@link SessionState#isConfigured()}, meaning new session
     * requests will be omitted.
     * </p>
     *
     * @param initialServerConfig the server configuration with which the session will be initialized. Can be {@code null}.
     * @param updatedServerConfig the server configuration with which the session will be updated. Can be {@code null}.
     */
    private void createAndAssignCurrentSession(ServerConfiguration initialServerConfig, ServerConfiguration updatedServerConfig) {
        SessionImpl session = sessionCreator.createSession(this);
        Beacon beacon = session.getBeacon();
        beacon.setServerConfigurationUpdateCallback(this);
        storeChildInList(session);
        lastInteractionTime = beacon.getSessionStartTime();
        topLevelActionCount = 0;
        if (initialServerConfig != null) {
            session.initializeServerConfiguration(initialServerConfig);
        }
        if (updatedServerConfig != null) {
            session.updateServerConfiguration(updatedServerConfig);
        }
        synchronized (lockObject) {
            // synchronize access
            currentSession = session;
        }
        this.beaconSender.addSession(session);
    }

    private void recordTopLevelEventInteraction() {
        lastInteractionTime = timingProvider.provideTimestampInMilliseconds();
    }

    private void recordTopActionEvent() {
        ++topLevelActionCount;
        recordTopLevelEventInteraction();
    }

    private void reTagCurrentSession() {
        if (lastUserTag == null || lastUserTag.length() == 0 || currentSession == null) {
            return;
        }
        currentSession.identifyUser(lastUserTag);
    }

    @Override
    public void onServerConfigurationUpdate(ServerConfiguration serverConfig) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
