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
package com.dynatrace.openkit.core.configuration;

import com.dynatrace.openkit.api.SSLTrustManager;
import com.dynatrace.openkit.api.http.HttpRequestInterceptor;
import com.dynatrace.openkit.api.http.HttpResponseInterceptor;

/**
 * The HTTPClientConfiguration holds all http client related settings
 */
public class HTTPClientConfiguration {

    private final String baseURL;

    private final int serverID;

    private final String applicationID;

    private final SSLTrustManager sslTrustManager;

    private final HttpRequestInterceptor httpRequestInterceptor;

    private final HttpResponseInterceptor httpResponseInterceptor;

    private final long deviceID;

    private HTTPClientConfiguration(Builder builder) {
        this.baseURL = builder.baseURL;
        this.serverID = builder.serverID;
        this.applicationID = builder.applicationID;
        this.sslTrustManager = builder.sslTrustManager;
        this.httpRequestInterceptor = builder.httpRequestInterceptor;
        this.httpResponseInterceptor = builder.httpResponseInterceptor;
        this.deviceID = builder.deviceID;
    }

    /**
     * Creates a new {@link HTTPClientConfiguration} instance and initializes it from the given
     * {@link OpenKitConfiguration}.
     *
     * @param openKitConfig the openKit configuration from which the instance will be initialized.
     * @return a new {@link HTTPClientConfiguration} instance initialized from the given configuration.
     */
    public static HTTPClientConfiguration from(OpenKitConfiguration openKitConfig) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a new builder instance and initializes it from the given {@link OpenKitConfiguration}
     *
     * @param openKitConfig the {@link OpenKitConfiguration} from which the builder will be initialized.
     * @return a pre initialized builder instance for creating a new {@link HTTPClientConfiguration}
     */
    public static Builder modifyWith(OpenKitConfiguration openKitConfig) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Creates a new builder instance and initializes it from the given {@link HTTPClientConfiguration}
     *
     * @param httpClientConfig the {@link HTTPClientConfiguration} from which the builder will be initialized.
     * @return a pre initialized builder instance for creating a new {@link HTTPClientConfiguration}
     */
    public static Builder modifyWith(HTTPClientConfiguration httpClientConfig) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the base url for the http client
     *
     * @return the base url
     */
    public String getBaseURL() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the server id to be used for the http client
     *
     * @return the server id
     */
    public int getServerID() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The application id for the http client
     *
     * @return the application id
     */
    public String getApplicationID() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns an interface used for X509 certificate authentication and hostname verification.
     */
    public SSLTrustManager getSSLTrustManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns an interface used to intercept HTTP requests, before they are sent to the Dynatrace backend.
     */
    public HttpRequestInterceptor getHttpRequestInterceptor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns an interface used to intercept HTTP responses received from Dynatrace backend.
     */
    public HttpResponseInterceptor getHttpResponseInterceptor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the unique device identifier
     */
    public long getDeviceID() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Builder class for building {@link HTTPClientConfiguration}.
     */
    public static final class Builder {

        private String baseURL = null;

        private int serverID = -1;

        private String applicationID = null;

        private SSLTrustManager sslTrustManager = null;

        private HttpRequestInterceptor httpRequestInterceptor = null;

        private HttpResponseInterceptor httpResponseInterceptor = null;

        private long deviceID;

        public Builder withBaseURL(String baseURL) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withServerID(int serverID) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withApplicationID(String applicationID) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withSSLTrustManager(SSLTrustManager sslTrustManager) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withHttpRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withHttpResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Builder withDeviceID(long deviceID) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public HTTPClientConfiguration build() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
