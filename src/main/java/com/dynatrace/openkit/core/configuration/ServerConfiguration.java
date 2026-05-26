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
package com.dynatrace.openkit.core.configuration;

import com.dynatrace.openkit.protocol.ResponseAttribute;
import com.dynatrace.openkit.protocol.ResponseAttributes;
import com.dynatrace.openkit.protocol.ResponseAttributesDefaults;

/**
 * Configuration class storing all configuration parameters as returned by Dynatrace.
 */
public class ServerConfiguration {

    private static final ResponseAttributesDefaults DEFAULT_VALUES = ResponseAttributesDefaults.UNDEFINED;

    /**
     * Default server configuration
     */
    public static final ServerConfiguration DEFAULT = ServerConfiguration.from(DEFAULT_VALUES);

    /**
     * Boolean indicating whether capturing is enabled by the backend or not
     */
    private final boolean isCaptureEnabled;

    /**
     * Boolean indicating whether crash reporting is enabled by the backend or not
     */
    private final boolean isCrashReportingEnabled;

    /**
     * Boolean indicating whether error reporting is enabled by the backend or not
     */
    private final boolean isErrorReportingEnabled;

    /**
     * The server ID to send future requests to
     */
    private final int serverID;

    /**
     * The maximum allowed beacon size (post body size) in bytes
     */
    private final int beaconSizeInBytes;

    /**
     * The multiplicity value
     */
    private final int multiplicity;

    /**
     * The send interval in milliseconds.
     */
    private final int sendIntervalInMilliseconds;

    /**
     * the maximum duration of a session
     */
    private final int maxSessionDurationInMilliseconds;

    /**
     * indicator whether session splitting by exceeding the max session duration is enabled or not.
     */
    private final boolean isSessionSplitBySessionDurationEnabled;

    /**
     * the maximum number of events per session
     */
    private final int maxEventsPerSession;

    /**
     * indicator whether session splitting by events is enabled or not
     */
    private final boolean isSessionSplitByEventsEnabled;

    /**
     * the session idle timeout in milliseconds
     */
    private final int sessionTimeoutInMilliseconds;

    /**
     * indicator whether session splitting by exceeding the idle timeout is enabled or not.
     */
    private final boolean isSessionSplitByIdleTimeoutEnabled;

    /**
     * version of the visit store that should be used
     */
    private final int visitStoreVersion;

    /**
     * session rate limiting percentage
     */
    private final int trafficControlPercentage;

    /**
     * Create a server configuration from a builder.
     *
     * @param builder The builder used to configure this instance.
     */
    private ServerConfiguration(Builder builder) {
        isCaptureEnabled = builder.isCaptureEnabled;
        isCrashReportingEnabled = builder.isCrashReportingEnabled;
        isErrorReportingEnabled = builder.isErrorReportingEnabled;
        serverID = builder.serverID;
        beaconSizeInBytes = builder.beaconSizeInBytes;
        multiplicity = builder.multiplicity;
        sendIntervalInMilliseconds = builder.sendIntervalInMilliseconds;
        maxSessionDurationInMilliseconds = builder.maxSessionDurationInMilliseconds;
        isSessionSplitBySessionDurationEnabled = builder.isSessionSplitBySessionDurationEnabled;
        maxEventsPerSession = builder.maxEventsPerSession;
        isSessionSplitByEventsEnabled = builder.isSessionSplitByEventsEnabled;
        sessionTimeoutInMilliseconds = builder.sessionTimeoutInMilliseconds;
        isSessionSplitByIdleTimeoutEnabled = builder.isSessionSplitByIdleTimeoutEnabled;
        visitStoreVersion = builder.visitStoreVersion;
        trafficControlPercentage = builder.trafficControlPercentage;
    }

    /**
     * Creates a new server configuration from the given {@link ResponseAttributes}
     *
     * @param responseAttributes the response attributes from which to create the server configuration.
     * @return the newly created server configuration.
     */
    public static ServerConfiguration from(ResponseAttributes responseAttributes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a boolean indicating whether capturing is enabled in Dynatrace or not.
     *
     * @return {@code true} if capturing is enabled, {@code false} otherwise.
     */
    public boolean isCaptureEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a boolean indicating whether crash reporting is enabled in Dynatrace or not.
     *
     * @return {@code true} if crash reporting is enabled, {@code false} otherwise.
     */
    public boolean isCrashReportingEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a boolean indicating whether error reporting is enabled in Dynatrace or not.
     *
     * @return {@code true} if error reporting is enabled, {@code false} otherwise.
     */
    public boolean isErrorReportingEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the server's id to communicate with.
     *
     * @return Server ID to communicate with
     */
    public int getServerID() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the maximum beacon size, that is the post body, in bytes.
     *
     * @return Maximum beacon size in bytes.
     */
    public int getBeaconSizeInBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get multiplicity value.
     *
     * <p>
     * Multiplicity is a factor on the server side, which is greater than 1 to prevent overload situations.
     * This value comes from the server and has to be sent back to the server.
     * </p>
     *
     * @return Multiplicity factor
     */
    public int getMultiplicity() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the send interval in milliseconds.
     *
     * @return send interval in milliseconds.
     */
    public int getSendIntervalInMilliseconds() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the maximum duration in milliseconds after which a session is to be split.
     *
     * @return the maximum duration of a session in milliseconds.
     */
    public int getMaxSessionDurationInMilliseconds() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns {@code true} if session splitting when exceeding the maximum session duration is enabled, {@code false}
     * otherwise.
     */
    public boolean isSessionSplitBySessionDurationEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the maximum number of events after which a session is to be split.
     *
     * @return the maximum number of top level actions per session.
     */
    public int getMaxEventsPerSession() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns {@code true} if session splitting when exceeding the maximum number of events is enabled, {@code false}
     * otherwise.
     */
    public boolean isSessionSplitByEventsEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the idle timeout after which a session is to be split.
     *
     * @return the idle timeout of a session.
     */
    public int getSessionTimeoutInMilliseconds() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns {@code true} if session splitting by exceeding the idle timeout is enabled, {@code false} otherwise.
     */
    public boolean isSessionSplitByIdleTimeoutEnabled() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the version of the visit store that should be used.
     *
     * @return version of the visit store.
     */
    public int getVisitStoreVersion() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a percentage value that is used for session rate limiting.
     *
     * @return percentage of sessions to be captured.
     */
    public int getTrafficControlPercentage() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a boolean indicating whether sending arbitrary data to the server is allowed or not.
     *
     * <p>
     * Sending data is only allowed if all of the following conditions evaluate to true.
     * * {@link #isCaptureEnabled()} is {@code true}
     * * {@link #getMultiplicity()} is greater than {@code 0}
     *
     * To check if sending errors is allowed, use {@link #isSendingErrorsAllowed()}.
     * To check if sending crashes is allowed, use {@link #isSendingCrashesAllowed()}.
     * </p>
     *
     * @return {@code true} if data sending is allowed, {@code false} otherwise.
     */
    public boolean isSendingDataAllowed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a boolean indicating whether sending crashes to the server is allowed or not.
     *
     * <p>
     * Sending crashes is only allowed if all of the following conditions evaluate to true.
     * * {@link #isSendingDataAllowed()} yields {@code true}
     * * {@link #isCrashReportingEnabled()} is {@code true}
     * </p>
     *
     * @return {@code true} if sending crashes is allowed, {@code false} otherwise.
     */
    public boolean isSendingCrashesAllowed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a boolean indicating whether sending errors to the server is allowed or not.
     *
     * <p>
     * Sending errors is only allowed if all of the following conditions evaluate to true.
     * * {@link #isSendingDataAllowed()} yields {@code true}
     * * {@link #isErrorReportingEnabled()} is {@code true}
     * </p>
     *
     * @return {@code true} if sending errors is allowed, {@code false} otherwise.
     */
    public boolean isSendingErrorsAllowed() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Merges given {@code other} with {@code this} instance and return the merged instance.
     *
     * <p>
     * Most fields are taken from {@code other}, except for the following which do not change:
     * </p>
     * <ul>
     *     <li>{@link #multiplicity}</li>
     *     <li>{@link #serverID}</li>
     *     <li>{@link #maxEventsPerSession}</li>
     *     <li>{@link #maxSessionDurationInMilliseconds}</li>
     *     <li>{@link #sessionTimeoutInMilliseconds}</li>
     *     <li>{@link #visitStoreVersion}</li>
     *     <li>{@link #trafficControlPercentage}</li>
     * </ul>
     *
     * @param other The other instance to merge with.
     * @return New {@link ServerConfiguration} instance with merged values.
     */
    public ServerConfiguration merge(ServerConfiguration other) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Builder class for creating a custom instance of {@link ServerConfiguration}.
     */
    public static final class Builder {

        private boolean isCaptureEnabled;

        private boolean isCrashReportingEnabled;

        private boolean isErrorReportingEnabled;

        private int serverID;

        private int beaconSizeInBytes;

        private int multiplicity;

        private int sendIntervalInMilliseconds;

        private int maxSessionDurationInMilliseconds;

        private boolean isSessionSplitBySessionDurationEnabled;

        private int maxEventsPerSession;

        private boolean isSessionSplitByEventsEnabled;

        private int sessionTimeoutInMilliseconds;

        private boolean isSessionSplitByIdleTimeoutEnabled;

        private int visitStoreVersion;

        private int trafficControlPercentage;

        /**
         * Construct and initialize fields from given {@link ResponseAttributes}.
         *
         * @param responseAttributes the attributes received as a response from the server.
         */
        public Builder(ResponseAttributes responseAttributes) {
            isCaptureEnabled = responseAttributes.isCapture();
            isCrashReportingEnabled = responseAttributes.isCaptureCrashes();
            isErrorReportingEnabled = responseAttributes.isCaptureErrors();
            serverID = responseAttributes.getServerId();
            beaconSizeInBytes = responseAttributes.getMaxBeaconSizeInBytes();
            multiplicity = responseAttributes.getMultiplicity();
            sendIntervalInMilliseconds = responseAttributes.getSendIntervalInMilliseconds();
            maxSessionDurationInMilliseconds = responseAttributes.getMaxSessionDurationInMilliseconds();
            isSessionSplitBySessionDurationEnabled = responseAttributes.isAttributeSet(ResponseAttribute.MAX_SESSION_DURATION);
            maxEventsPerSession = responseAttributes.getMaxEventsPerSession();
            isSessionSplitByEventsEnabled = responseAttributes.isAttributeSet(ResponseAttribute.MAX_EVENTS_PER_SESSION);
            sessionTimeoutInMilliseconds = responseAttributes.getSessionTimeoutInMilliseconds();
            isSessionSplitByIdleTimeoutEnabled = responseAttributes.isAttributeSet(ResponseAttribute.SESSION_TIMEOUT);
            visitStoreVersion = responseAttributes.getVisitStoreVersion();
            trafficControlPercentage = responseAttributes.getTrafficControlPercentage();
        }

        /**
         * Construct and initialize fields from given {@link ServerConfiguration}.
         */
        public Builder(ServerConfiguration serverConfiguration) {
            isCaptureEnabled = serverConfiguration.isCaptureEnabled();
            isCrashReportingEnabled = serverConfiguration.isCrashReportingEnabled();
            isErrorReportingEnabled = serverConfiguration.isErrorReportingEnabled();
            serverID = serverConfiguration.getServerID();
            beaconSizeInBytes = serverConfiguration.getBeaconSizeInBytes();
            multiplicity = serverConfiguration.getMultiplicity();
            sendIntervalInMilliseconds = serverConfiguration.getSendIntervalInMilliseconds();
            maxSessionDurationInMilliseconds = serverConfiguration.getMaxSessionDurationInMilliseconds();
            isSessionSplitBySessionDurationEnabled = serverConfiguration.isSessionSplitBySessionDurationEnabled();
            maxEventsPerSession = serverConfiguration.getMaxEventsPerSession();
            isSessionSplitByEventsEnabled = serverConfiguration.isSessionSplitByEventsEnabled();
            sessionTimeoutInMilliseconds = serverConfiguration.getSessionTimeoutInMilliseconds();
            isSessionSplitByIdleTimeoutEnabled = serverConfiguration.isSessionSplitByIdleTimeoutEnabled();
            visitStoreVersion = serverConfiguration.getVisitStoreVersion();
            trafficControlPercentage = serverConfiguration.getTrafficControlPercentage();
        }

        /**
         * Enables/disables capturing by setting {@link #isCaptureEnabled} to the corresponding value.
         *
         * @return {@code this}
         */
        public Builder withCapture(boolean captureState) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Enables/disables crash reporting by setting {@link #isCrashReportingEnabled} to the corresponding value.
         *
         * @return {@code this}
         */
        public Builder withCrashReporting(boolean crashReportingState) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Enables/disables error reporting by setting {@link #isErrorReportingEnabled} to the corresponding value.
         *
         * @return {@code this}
         */
        public Builder withErrorReporting(boolean errorReportingState) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Configures the server ID.
         *
         * @param serverID The server ID to communicate with.
         * @return {@code this}
         */
        public Builder withServerID(int serverID) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Configures the beacon size in Bytes.
         *
         * @param beaconSizeInBytes Maximum allowed beacon size in bytes.
         * @return {@code this}
         */
        public Builder withBeaconSizeInBytes(int beaconSizeInBytes) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Configures the multiplicity factor.
         *
         * @param multiplicity Multiplicity factor.
         * @return {@code this}
         */
        public Builder withMultiplicity(int multiplicity) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Configure the send interval in milliseconds.
         *
         * @param sendIntervalInMilliseconds Send interval in milliseconds
         * @return {@code this}
         */
        public Builder withSendIntervalInMilliseconds(int sendIntervalInMilliseconds) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Configures the maximum duration of a session, after which the session gets split.
         *
         * @param maxSessionDurationInMillis the maximum duration of a session in milliseconds
         * @return {@code this}
         */
        public Builder withMaxSessionDurationInMilliseconds(int maxSessionDurationInMillis) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Configures the maximum number of events per session, after which the session gets split.
         *
         * @param maxEventsPerSession the maximum number of top level actions after which a session gets split.
         * @return {@code this}
         */
        public Builder withMaxEventsPerSession(int maxEventsPerSession) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Configures the idle timeout after which a session gets split.
         *
         * @param sessionTimeoutInMilliseconds the idle timeout in milliseconds after which a session gets split.
         * @return {@code this}
         */
        public Builder withSessionTimeoutInMilliseconds(int sessionTimeoutInMilliseconds) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Configures the version of the visit store that should be used.
         *
         * @param visitStoreVersion the version of the visit store to be used.
         * @return {@code this}
         */
        public Builder withVisitStoreVersion(int visitStoreVersion) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Configures the traffic control/cost control percentage.
         *
         * <p>
         *     This value is used as rate limit to limit the number of sessions being captured.
         * </p>
         *
         * @param trafficControlPercentage Percentage of sessions being captured.
         * @return {@code this}
         */
        public Builder withTrafficControlPercentage(int trafficControlPercentage) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Build the {@link ServerConfiguration} and return the new instance.
         *
         * @return Newly created {@link ServerConfiguration} instance.
         */
        public ServerConfiguration build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
