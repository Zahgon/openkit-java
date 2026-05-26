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
package com.dynatrace.openkit.protocol;

import java.util.EnumSet;

/**
 * Implements {@link ResponseAttributes} providing all the attributes received from the server.
 */
public class ResponseAttributesImpl implements ResponseAttributes {

    /**
     * Represents the set of attributes which are set / were sent by the server.
     */
    private final EnumSet<ResponseAttribute> setAttributes;

    private final int maxBeaconSizeInBytes;

    private final int maxSessionDurationInMilliseconds;

    private final int maxEventsPerSession;

    private final int sessionTimeoutInMilliseconds;

    private final int sendIntervalInMilliseconds;

    private final int visitStoreVersion;

    private final boolean isCapture;

    private final boolean isCaptureCrashes;

    private final boolean isCaptureErrors;

    private final int trafficControlPercentage;

    private final String applicationId;

    private final int multiplicity;

    private final int serverId;

    private final String status;

    private final long timestampInMilliseconds;

    private ResponseAttributesImpl(Builder builder) {
        setAttributes = EnumSet.copyOf(builder.setAttributes);
        maxBeaconSizeInBytes = builder.maxBeaconSizeInBytes;
        maxSessionDurationInMilliseconds = builder.maxSessionDurationInMilliseconds;
        maxEventsPerSession = builder.maxEventsPerSession;
        sessionTimeoutInMilliseconds = builder.sessionTimeoutInMilliseconds;
        sendIntervalInMilliseconds = builder.sendIntervalInMilliseconds;
        visitStoreVersion = builder.visitStoreVersion;
        isCapture = builder.isCapture;
        isCaptureCrashes = builder.isCaptureCrashes;
        isCaptureErrors = builder.isCaptureErrors;
        trafficControlPercentage = builder.trafficControlPercentage;
        applicationId = builder.applicationId;
        multiplicity = builder.multiplicity;
        serverId = builder.serverId;
        status = builder.status;
        timestampInMilliseconds = builder.timestampInMilliseconds;
    }

    @Override
    public int getMaxBeaconSizeInBytes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getMaxSessionDurationInMilliseconds() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getMaxEventsPerSession() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getSessionTimeoutInMilliseconds() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getSendIntervalInMilliseconds() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getVisitStoreVersion() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isCapture() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isCaptureCrashes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isCaptureErrors() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getTrafficControlPercentage() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getApplicationId() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getMultiplicity() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int getServerId() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String getStatus() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public long getTimestampInMilliseconds() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isAttributeSet(ResponseAttribute attribute) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public ResponseAttributes merge(ResponseAttributes responseAttributes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void applyBeaconSize(Builder builder, ResponseAttributes responseAttributes) {
        if (!responseAttributes.isAttributeSet(ResponseAttribute.MAX_BEACON_SIZE)) {
            return;
        }
        builder.withMaxBeaconSizeInBytes(responseAttributes.getMaxBeaconSizeInBytes());
    }

    private void applySessionDuration(Builder builder, ResponseAttributes responseAttributes) {
        if (!responseAttributes.isAttributeSet(ResponseAttribute.MAX_SESSION_DURATION)) {
            return;
        }
        builder.withMaxSessionDurationInMilliseconds(responseAttributes.getMaxSessionDurationInMilliseconds());
    }

    private void applyEventsPerSession(Builder builder, ResponseAttributes responseAttributes) {
        if (!responseAttributes.isAttributeSet(ResponseAttribute.MAX_EVENTS_PER_SESSION)) {
            return;
        }
        builder.withMaxEventsPerSession(responseAttributes.getMaxEventsPerSession());
    }

    private void applySessionTimeout(Builder builder, ResponseAttributes responseAttributes) {
        if (!responseAttributes.isAttributeSet(ResponseAttribute.SESSION_TIMEOUT)) {
            return;
        }
        builder.withSessionTimeoutInMilliseconds(responseAttributes.getSessionTimeoutInMilliseconds());
    }

    private void applySendInterval(Builder builder, ResponseAttributes responseAttributes) {
        if (!responseAttributes.isAttributeSet(ResponseAttribute.SEND_INTERVAL)) {
            return;
        }
        builder.withSendIntervalInMilliseconds(responseAttributes.getSendIntervalInMilliseconds());
    }

    private void applyVisitStoreVersion(Builder builder, ResponseAttributes responseAttributes) {
        if (!responseAttributes.isAttributeSet(ResponseAttribute.VISIT_STORE_VERSION)) {
            return;
        }
        builder.withVisitStoreVersion(responseAttributes.getVisitStoreVersion());
    }

    private void applyCapture(Builder builder, ResponseAttributes responseAttributes) {
        if (!responseAttributes.isAttributeSet(ResponseAttribute.IS_CAPTURE)) {
            return;
        }
        builder.withCapture(responseAttributes.isCapture());
    }

    private void applyCaptureCrashes(Builder builder, ResponseAttributes responseAttributes) {
        if (!responseAttributes.isAttributeSet(ResponseAttribute.IS_CAPTURE_CRASHES)) {
            return;
        }
        builder.withCaptureCrashes(responseAttributes.isCaptureCrashes());
    }

    private void applyCaptureErrors(Builder builder, ResponseAttributes responseAttributes) {
        if (!responseAttributes.isAttributeSet(ResponseAttribute.IS_CAPTURE_ERRORS)) {
            return;
        }
        builder.withCaptureErrors(responseAttributes.isCaptureErrors());
    }

    private void applyApplicationId(Builder builder, ResponseAttributes responseAttributes) {
        if (!responseAttributes.isAttributeSet(ResponseAttribute.APPLICATION_ID)) {
            return;
        }
        builder.withApplicationId(responseAttributes.getApplicationId());
    }

    private void applyTrafficControlPercentage(Builder builder, ResponseAttributes responseAttributes) {
        if (!responseAttributes.isAttributeSet(ResponseAttribute.TRAFFIC_CONTROL_PERCENTAGE)) {
            return;
        }
        builder.withTrafficControlPercentage(responseAttributes.getTrafficControlPercentage());
    }

    private void applyMultiplicity(Builder builder, ResponseAttributes responseAttributes) {
        if (!responseAttributes.isAttributeSet(ResponseAttribute.MULTIPLICITY)) {
            return;
        }
        builder.withMultiplicity(responseAttributes.getMultiplicity());
    }

    private void applyServerId(Builder builder, ResponseAttributes responseAttributes) {
        if (!responseAttributes.isAttributeSet(ResponseAttribute.SERVER_ID)) {
            return;
        }
        builder.withServerId(responseAttributes.getServerId());
    }

    private void applyStatus(Builder builder, ResponseAttributes responseAttributes) {
        if (!responseAttributes.isAttributeSet(ResponseAttribute.STATUS)) {
            return;
        }
        builder.withStatus(responseAttributes.getStatus());
    }

    private void applyTimestamp(Builder builder, ResponseAttributes responseAttributes) {
        if (!responseAttributes.isAttributeSet(ResponseAttribute.TIMESTAMP)) {
            return;
        }
        builder.withTimestampInMilliseconds(responseAttributes.getTimestampInMilliseconds());
    }

    /**
     * Creates a new builder initialized with the defaults value for {@link KeyValueResponseParser key-value parsing}.
     */
    public static Builder withKeyValueDefaults() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a new builder initialized with the default values for {@link JsonResponseParser JSON parsing}.
     */
    public static Builder withJsonDefaults() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a new builder instance with undefined default values.
     */
    public static Builder withUndefinedDefaults() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static class Builder {

        private final EnumSet<ResponseAttribute> setAttributes = EnumSet.noneOf(ResponseAttribute.class);

        private int maxBeaconSizeInBytes;

        private int maxSessionDurationInMilliseconds;

        private int maxEventsPerSession;

        private int sessionTimeoutInMilliseconds;

        private int sendIntervalInMilliseconds;

        private int visitStoreVersion;

        private boolean isCapture;

        private boolean isCaptureCrashes;

        private boolean isCaptureErrors;

        private int trafficControlPercentage;

        private String applicationId;

        private int multiplicity;

        private int serverId;

        private String status;

        private long timestampInMilliseconds;

        private Builder(ResponseAttributes defaults) {
            maxBeaconSizeInBytes = defaults.getMaxBeaconSizeInBytes();
            maxSessionDurationInMilliseconds = defaults.getMaxSessionDurationInMilliseconds();
            maxEventsPerSession = defaults.getMaxEventsPerSession();
            sessionTimeoutInMilliseconds = defaults.getSessionTimeoutInMilliseconds();
            sendIntervalInMilliseconds = defaults.getSendIntervalInMilliseconds();
            visitStoreVersion = defaults.getVisitStoreVersion();
            isCapture = defaults.isCapture();
            isCaptureCrashes = defaults.isCaptureCrashes();
            isCaptureErrors = defaults.isCaptureErrors();
            trafficControlPercentage = defaults.getTrafficControlPercentage();
            applicationId = defaults.getApplicationId();
            multiplicity = defaults.getMultiplicity();
            serverId = defaults.getServerId();
            status = defaults.getStatus();
            timestampInMilliseconds = defaults.getTimestampInMilliseconds();
            for (ResponseAttribute attribute : ResponseAttribute.values()) {
                if (defaults.isAttributeSet(attribute)) {
                    setAttribute(attribute);
                }
            }
        }

        /**
         * Sets the maximum beacon size in bytes
         *
         * @param maxBeaconSizeInBytes the maximum size in bytes when sending beacon data.
         * @return {@code this}
         */
        public Builder withMaxBeaconSizeInBytes(int maxBeaconSizeInBytes) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum duration after which a session is to be split.
         *
         * @param maxSessionDurationInMilliseconds maximum duration of a session in milliseconds.
         * @return {@code this}
         */
        public Builder withMaxSessionDurationInMilliseconds(int maxSessionDurationInMilliseconds) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the maximum number of top level actions after which a session is to be split.
         *
         * @param maxEventsPerSession maximum number of top level actions
         * @return {@code this}
         */
        public Builder withMaxEventsPerSession(int maxEventsPerSession) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the idle timeout after which a session is to be split.
         *
         * @param sessionTimeoutInMilliseconds the maximum idle timeout of a session in milliseconds
         * @return {@code this}
         */
        public Builder withSessionTimeoutInMilliseconds(int sessionTimeoutInMilliseconds) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the send interval in milliseconds.
         *
         * @param sendIntervalInMilliseconds send interval in milliseconds.
         * @return {@code this}
         */
        public Builder withSendIntervalInMilliseconds(int sendIntervalInMilliseconds) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the version of the visit store that should be used.
         *
         * @param visitStoreVersion version of the visit store
         * @return {@code this}
         */
        public Builder withVisitStoreVersion(int visitStoreVersion) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets whether capturing is enabled/disabled.
         *
         * @param isCapture capture state
         * @return {@code this}
         */
        public Builder withCapture(boolean isCapture) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets whether capturing of crashes is enabled/disabled.
         *
         * @param isCaptureCrashes crash capture state
         * @return {@code this}
         */
        public Builder withCaptureCrashes(boolean isCaptureCrashes) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets whether capturing of errors is enabled/disabled.
         *
         * @param isCaptureErrors error capture state.
         * @return {@code this}
         */
        public Builder withCaptureErrors(boolean isCaptureErrors) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets a session sampling percentage (known as Cost Control).
         *
         * @param trafficControlPercentage Percentage of sessions to capture
         * @return {@code this}
         */
        public Builder withTrafficControlPercentage(int trafficControlPercentage) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Set application UUID to which this configuration belongs to.
         *
         * @param applicationId application's UUID
         * @return {@code this}
         */
        public Builder withApplicationId(String applicationId) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the multiplicity
         *
         * @param multiplicity multiplicity
         * @return {@code this}
         */
        public Builder withMultiplicity(int multiplicity) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the ID of the server to which data should be sent to.
         *
         * @param serverId the ID of the server to communicate with.
         * @return {@code this}
         */
        public Builder withServerId(int serverId) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the response status received for a new session request.
         *
         * @param status the status received for new session request.
         * @return {@code this}
         */
        public Builder withStatus(String status) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Sets the timestamp of the configuration sent by the sever.
         *
         * @param timestampInMilliseconds the timestamp of the configuration in milliseconds.
         * @return {@code this}
         */
        public Builder withTimestampInMilliseconds(long timestampInMilliseconds) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        /**
         * Creates a new {@link ResponseAttributes} instance with all the attributes set in this builder.
         */
        public ResponseAttributes build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private void setAttribute(ResponseAttribute attribute) {
            setAttributes.add(attribute);
        }
    }
}
