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
package com.dynatrace.openkit.core.communication;

import com.dynatrace.openkit.api.Logger;
import com.dynatrace.openkit.core.configuration.HTTPClientConfiguration;
import com.dynatrace.openkit.core.configuration.ServerConfiguration;
import com.dynatrace.openkit.core.objects.SessionImpl;
import com.dynatrace.openkit.core.objects.SessionState;
import com.dynatrace.openkit.protocol.AdditionalQueryParameters;
import com.dynatrace.openkit.protocol.HTTPClient;
import com.dynatrace.openkit.protocol.ResponseAttribute;
import com.dynatrace.openkit.protocol.ResponseAttributes;
import com.dynatrace.openkit.protocol.ResponseAttributesImpl;
import com.dynatrace.openkit.protocol.StatusResponse;
import com.dynatrace.openkit.providers.HTTPClientProvider;
import com.dynatrace.openkit.providers.TimingProvider;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * State context for beacon sending states.
 *
 * <p>
 * All package-private methods in this class shall only be accessed from the beacon sending thread,
 * since they are not thread safe.
 * All public methods are thread safe, unless explicitly stated.
 * </p>
 */
public class BeaconSendingContext implements AdditionalQueryParameters {

    /**
     * Default sleep time in milliseconds (used by {@link #sleep()}).
     */
    static final long DEFAULT_SLEEP_TIME_MILLISECONDS = TimeUnit.SECONDS.toMillis(1);

    private final Logger logger;

    /**
     * synchronization object for updating and reading server configuration and last response attributes
     */
    private final Object lockObject = new Object();

    /**
     * Configuration storing last valid server side configuration.
     */
    private ServerConfiguration serverConfiguration;

    /**
     * Represents the last status response received from the server.
     *
     * <p>
     * This field will be initially filled with the first response from the server when OpenKit initializes.
     * Subsequent server responses (e.g. from session requests) will update the last server response by merging
     * received fields.
     * </p>
     */
    private ResponseAttributes lastResponseAttributes;

    /**
     * Configuration storing last valid HTTP client configuration, independent of a session.
     */
    private HTTPClientConfiguration httpClientConfiguration;

    private final HTTPClientProvider httpClientProvider;

    private final TimingProvider timingProvider;

    /**
     * container storing all sessions
     */
    private final LinkedBlockingQueue<SessionImpl> sessions = new LinkedBlockingQueue<>();

    /**
     * boolean indicating whether shutdown was requested or not
     */
    private final AtomicBoolean shutdown = new AtomicBoolean(false);

    /**
     * countdown latch updated when init was done - which can either be success or failure
     */
    private final CountDownLatch initCountDownLatch = new CountDownLatch(1);

    /**
     * current state of beacon sender
     */
    private AbstractBeaconSendingState currentState;

    /**
     * state following after current state, nextState is usually set by doExecute of the current state
     */
    private AbstractBeaconSendingState nextState;

    /**
     * timestamp when open sessions were last sent
     */
    private long lastOpenSessionBeaconSendTime;

    /**
     * timestamp when last status check was done
     */
    private long lastStatusCheckTime;

    /**
     * boolean indicating whether init was successful or not
     */
    private volatile boolean initSucceeded = false;

    /**
     * Constructor.
     *
     * <p>
     * The state is initialized to {@link BeaconSendingInitState},
     * </p>
     */
    public BeaconSendingContext(Logger logger, HTTPClientConfiguration httpClientConfiguration, HTTPClientProvider httpClientProvider, TimingProvider timingProvider) {
        this(logger, httpClientConfiguration, httpClientProvider, timingProvider, new BeaconSendingInitState());
    }

    /**
     * Constructor.
     *
     * <p>
     * The initial state is provided. This constructor is intended for unit testing.
     * </p>
     */
    BeaconSendingContext(Logger logger, HTTPClientConfiguration httpClientConfiguration, HTTPClientProvider httpClientProvider, TimingProvider timingProvider, AbstractBeaconSendingState initialState) {
        this.logger = logger;
        this.httpClientConfiguration = httpClientConfiguration;
        this.serverConfiguration = ServerConfiguration.DEFAULT;
        this.httpClientProvider = httpClientProvider;
        this.timingProvider = timingProvider;
        this.lastResponseAttributes = ResponseAttributesImpl.withUndefinedDefaults().build();
        currentState = initialState;
    }

    /**
     * Executes the current state.
     *
     * <p>
     * This method must only be called from the beacon sending thread, since it's not thread safe.
     * </p>
     */
    public void executeCurrentState() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Requests a shutdown.
     */
    public void requestShutdown() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets a boolean flag indicating whether shutdown was requested before or not.
     */
    public boolean isShutdownRequested() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Wait until OpenKit has been fully initialized.
     *
     * <p>
     * If initialization is interrupted (e.g. {@link #requestShutdown()} was called), then this method also returns.
     * </p>
     *
     * @return {@code true} OpenKit is fully initialized, {@code false} OpenKit init got interrupted.
     */
    public boolean waitForInit() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Wait until OpenKit has been fully initialized or timeout expired.
     *
     * <p>
     * If initialization is interrupted (e.g. {@link #requestShutdown()} was called), then this method also returns.
     * </p>
     *
     * @param timeoutMillis The maximum number of milliseconds to wait for initialization being completed.
     * @return {@code true} if OpenKit is fully initialized, {@code false} if OpenKit init got interrupted or time to wait expired.
     */
    public boolean waitForInit(long timeoutMillis) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a boolean indicating whether OpenKit is initialized or not.
     *
     * @return {@code true} if OpenKit is initialized, {@code false} otherwise.
     */
    public boolean isInitialized() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets a boolean indicating whether the current state is a terminal state or not.
     *
     * <p>
     * This method must only be called from the beacon sending thread, since it's not thread safe.
     * </p>
     *
     * @return {@code true} if the current state is a terminal state, {@code false} otherwise.
     */
    public boolean isInTerminalState() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets a boolean flag indicating whether capturing is turned on or off.
     *
     * @return {@code true} if capturing is turned on, {@code false} otherwise.
     */
    boolean isCaptureOn() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the current state.
     *
     * @return current state.
     */
    AbstractBeaconSendingState getCurrentState() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the next state.
     *
     * @param nextState Next state when state transition is performed.
     */
    void setNextState(AbstractBeaconSendingState nextState) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the next state.
     *
     * @return the nextState
     */
    AbstractBeaconSendingState getNextState() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Complete OpenKit initialization.
     *
     * <p>
     * This will wake up every caller waiting in the {@link #waitForInit()} method.
     * </p>
     *
     * @param success {@code true} if OpenKit was successfully initialized, {@code false} if it was interrupted.
     */
    void initCompleted(boolean success) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the HTTP client provider.
     *
     * @return A class responsible for retrieving an instance of {@link HTTPClient}.
     */
    HTTPClientProvider getHTTPClientProvider() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Convenience method to retrieve an {@link HTTPClient} instance with {@link #httpClientConfiguration}
     *
     * <p>
     * This method is only allowed to be called from within the beacon sending thread.
     * </p>
     *
     * @return HTTP client received from {@link HTTPClientProvider}.
     */
    HTTPClient getHTTPClient() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Convenience method to retrieve an HTTP client.
     *
     * @return HTTP client received from {@link HTTPClientProvider}.
     */
    HTTPClient getHTTPClient(HTTPClientConfiguration httpClientConfiguration) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Gets the current timestamp.
     *
     * @return current timestamp as milliseconds elapsed since epoch (1970-01-01T00:00:00.000)
     */
    long getCurrentTimestamp() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sleep some time ({@link #DEFAULT_SLEEP_TIME_MILLISECONDS}.
     *
     * @throws InterruptedException When sleeping thread got interrupted.
     */
    void sleep() throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sleep given amount of milliseconds.
     *
     * @param millis The number of milliseconds to sleep.
     * @throws InterruptedException When sleeping thread got interrupted.
     */
    void sleep(long millis) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get timestamp when open sessions were sent last.
     */
    long getLastOpenSessionBeaconSendTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set timestamp when open sessions were sent last.
     */
    void setLastOpenSessionBeaconSendTime(long timestamp) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get timestamp when last status check was performed.
     */
    long getLastStatusCheckTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Set timestamp when last status check was performed.
     */
    void setLastStatusCheckTime(long timestamp) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the send interval for open sessions.
     */
    int getSendInterval() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the last {@link ResponseAttributes} received from the server.
     */
    ResponseAttributes getLastResponseAttributes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the last known {@link ServerConfiguration}.
     */
    public ServerConfiguration getLastServerConfiguration() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Disable data capturing and clears all session data. Finished sessions are removed from the beacon.
     */
    void disableCaptureAndClear() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Disables data capturing
     */
    private void disableCapture() {
        synchronized (lockObject) {
            serverConfiguration = new ServerConfiguration.Builder(serverConfiguration).withCapture(false).build();
        }
    }

    /**
     * Handle the status response received from the server.
     */
    void handleStatusResponse(StatusResponse receivedResponse) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Updates the {@link #getLastResponseAttributes() last known response attributes} as well as the
     * {@link ServerConfiguration} and {@link HTTPClientConfiguration} in case the given status response
     * {@link BeaconSendingResponseUtil#isSuccessfulResponse(StatusResponse) is succesful}.
     *
     * @param statusResponse the status response from which to update the last response attributes.
     * @return in case the given status response was successful the updated response attributes are returned. Otherwise
     * the current response attributes are returned.
     */
    ResponseAttributes updateFrom(StatusResponse statusResponse) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Ensure that the application id coming with the response matches the one that was configured for OpenKit.
     *
     * <p>
     *     Mismatch check prevents a rare Jetty bug, where responses might be dispatched to the wrong receiver.
     * </p>
     *
     * @param lastResponseAttributes The last response attributes received from Dynatrace.
     *
     * @return {@code false} if application id is matching, {@code true} if a mismatch occurred.
     */
    boolean isApplicationIdMismatch(ResponseAttributes lastResponseAttributes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    HTTPClientConfiguration createHttpClientConfigurationWith(int serverId) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Clear captured data from all sessions.
     */
    private void clearAllSessionData() {
        // iterate over the elements
        Iterator<SessionImpl> iterator = sessions.iterator();
        while (iterator.hasNext()) {
            SessionImpl session = iterator.next();
            session.clearCapturedData();
            SessionState state = session.getState();
            if (state.isFinished()) {
                iterator.remove();
            }
        }
    }

    /**
     * Get all sessions that are not yet configured.
     *
     * <p>
     * A session is considered as not configured if it did not receive a server configuration update (either
     * when receiving a successful for the first new session request or when capturing for the session got
     * disabled due to an unsuccessful response).
     * </p>
     *
     * <p>
     * The returned list is a snapshot and might change during traversal.
     * </p>
     *
     * @return A list of new sessions.
     */
    List<SessionImpl> getAllNotConfiguredSessions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a list of all sessions that have been configured and are currently open.
     */
    List<SessionImpl> getAllOpenAndConfiguredSessions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a list of all sessions that have been configured and are currently finished.
     */
    List<SessionImpl> getAllFinishedAndConfiguredSessions() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the number of sessions currently known to this context
     */
    int getSessionCount() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the current server ID to be used for creating new sessions
     */
    public int getCurrentServerId() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds the given session to the internal container of sessions.
     *
     * @param session The new session to add.
     */
    public void addSession(SessionImpl session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Removes the given {@link SessionImpl session} from the sessions known by this context.
     *
     * @param session the session to be removed.
     */
    boolean removeSession(SessionImpl session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// AdditionalQueryParameters
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public long getConfigurationTimestamp() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
