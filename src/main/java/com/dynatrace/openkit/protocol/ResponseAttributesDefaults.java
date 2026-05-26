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

import java.util.concurrent.TimeUnit;

public enum ResponseAttributesDefaults implements ResponseAttributes {

    JSON_RESPONSE {

        // 150 kB
        private final int DEFAULT_BEACON_SIZE_IN_BYTES = 150 * 1024;

        // 360 minutes
        private final int DEFAULT_SESSION_DURATION_IN_MILLIS = (int) TimeUnit.MINUTES.toMillis(360);

        private final int DEFAULT_EVENTS_PER_SESSION = 200;

        // 600 seconds
        private final int DEFAULT_SESSION_TIMEOUT_IN_MILLIS = (int) TimeUnit.SECONDS.toMillis(600);

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
    }
    , KEY_VALUE_RESPONSE {

        // 30 kB
        private final int DEFAULT_BEACON_SIZE_IN_BYTES = 30 * 1024;

        private final int DEFAULT_SESSION_DURATION_IN_MILLIS = -1;

        private final int DEFAULT_EVENTS_PER_SESSION = -1;

        private final int DEFAULT_SESSION_TIMEOUT_IN_MILLIS = -1;

        private final int DEFAULT_SEND_INTERVAL_IN_MILLIS = (int) TimeUnit.SECONDS.toMillis(120);

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
    }
    , UNDEFINED {

        // 30 kB
        private final int DEFAULT_BEACON_SIZE_IN_BYTES = 30 * 1024;

        private final int DEFAULT_SESSION_DURATION_IN_MILLIS = -1;

        private final int DEFAULT_EVENTS_PER_SESSION = -1;

        private final int DEFAULT_SESSION_TIMEOUT_IN_MILLIS = -1;

        private final int DEFAULT_SERVER_ID = -1;

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
        public int getServerId() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
    ;

    private static final int DEFAULT_VISIT_STORE_VERSION = 1;

    private static final boolean DEFAULT_CAPTURE = true;

    private static final boolean DEFAULT_CAPTURE_CRASHES = true;

    private static final boolean DEFAULT_CAPTURE_ERRORS = true;

    private static final int DEFAULT_TRAFFIC_CONTROL_PERCENTAGE = 100;

    private static final String DEFAULT_APPLICATION_ID = null;

    private static final int DEFAULT_MULTIPLICITY = 1;

    private static final int DEFAULT_SERVER_ID = 1;

    private static final String DEFAULT_STATUS = null;

    private static final int DEFAULT_TIMESTAMP = 0;

    // 120 seconds
    private final int DEFAULT_SEND_INTERVAL_IN_MILLIS = (int) TimeUnit.SECONDS.toMillis(120);

    public abstract int getMaxBeaconSizeInBytes();

    public abstract int getMaxSessionDurationInMilliseconds();

    public abstract int getMaxEventsPerSession();

    public abstract int getSessionTimeoutInMilliseconds();

    public int getSendIntervalInMilliseconds() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getVisitStoreVersion() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isCapture() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isCaptureCrashes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isCaptureErrors() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getTrafficControlPercentage() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getApplicationId() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getMultiplicity() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public int getServerId() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getStatus() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

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
}
