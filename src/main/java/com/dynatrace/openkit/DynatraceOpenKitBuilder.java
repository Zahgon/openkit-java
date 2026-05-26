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
package com.dynatrace.openkit;

import com.dynatrace.openkit.api.LogLevel;
import com.dynatrace.openkit.api.Logger;
import com.dynatrace.openkit.api.OpenKit;
import com.dynatrace.openkit.api.OpenKitConstants;
import com.dynatrace.openkit.api.SSLTrustManager;
import com.dynatrace.openkit.api.http.HttpRequestInterceptor;
import com.dynatrace.openkit.api.http.HttpResponseInterceptor;
import com.dynatrace.openkit.core.configuration.ConfigurationDefaults;
import com.dynatrace.openkit.core.objects.OpenKitImpl;
import com.dynatrace.openkit.core.objects.OpenKitInitializerImpl;
import com.dynatrace.openkit.core.util.DefaultLogger;
import com.dynatrace.openkit.core.util.StringUtil;
import com.dynatrace.openkit.protocol.http.NullHttpRequestInterceptor;
import com.dynatrace.openkit.protocol.http.NullHttpResponseInterceptor;
import com.dynatrace.openkit.protocol.ssl.SSLStrictTrustManager;

/**
 * Concrete builder that creates an {@code OpenKit} instance for Dynatrace Saas/Managed
 */
public class DynatraceOpenKitBuilder {

    // immutable fields
    private final String endpointURL;

    private final long deviceID;

    private final String origDeviceID;

    /**
     * The default server ID to communicate with.
     */
    public static final int DEFAULT_SERVER_ID = 1;

    /**
     * A string, identifying the type of OpenKit this builder is made for.
     */
    public static final String OPENKIT_TYPE = "DynatraceOpenKit";

    // mutable fields
    private Logger logger;

    private SSLTrustManager trustManager = new SSLStrictTrustManager();

    private LogLevel logLevel = LogLevel.WARN;

    private String operatingSystem = OpenKitConstants.DEFAULT_OPERATING_SYSTEM;

    private String manufacturer = OpenKitConstants.DEFAULT_MANUFACTURER;

    private String modelID = OpenKitConstants.DEFAULT_MODEL_ID;

    private String applicationVersion = OpenKitConstants.DEFAULT_APPLICATION_VERSION;

    private long beaconCacheMaxRecordAge = ConfigurationDefaults.DEFAULT_MAX_RECORD_AGE_IN_MILLIS;

    private long beaconCacheLowerMemoryBoundary = ConfigurationDefaults.DEFAULT_LOWER_MEMORY_BOUNDARY_IN_BYTES;

    private long beaconCacheUpperMemoryBoundary = ConfigurationDefaults.DEFAULT_UPPER_MEMORY_BOUNDARY_IN_BYTES;

    private DataCollectionLevel dataCollectionLevel = ConfigurationDefaults.DEFAULT_DATA_COLLECTION_LEVEL;

    private CrashReportingLevel crashReportLevel = ConfigurationDefaults.DEFAULT_CRASH_REPORTING_LEVEL;

    private HttpRequestInterceptor httpRequestInterceptor = NullHttpRequestInterceptor.INSTANCE;

    private HttpResponseInterceptor httpResponseInterceptor = NullHttpResponseInterceptor.INSTANCE;

    private final String applicationID;

    /**
     * Creates a new instance of type DynatraceOpenKitBuilder
     *
     * @param endpointURL   endpoint OpenKit connects to
     * @param applicationID unique application id
     * @param deviceID      unique device id
     */
    public DynatraceOpenKitBuilder(String endpointURL, String applicationID, long deviceID) {
        this.endpointURL = endpointURL;
        this.deviceID = deviceID;
        this.origDeviceID = String.valueOf(deviceID);
        this.applicationID = applicationID;
    }

    /**
     * Sets the default log level if the default logger is used.
     * If a custom logger is provided by calling {@link #withLogger(Logger)}, debug and info log output
     * depends on the values returned by {@link Logger#isDebugEnabled()} and {@link Logger#isInfoEnabled()}.
     *
     * @param level The logLevel for the custom logger
     * @return {@link DynatraceOpenKitBuilder}
     */
    public DynatraceOpenKitBuilder withLogLevel(LogLevel level) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the logger. If no logger is set the default console logger is used. For the default
     * logger verbose mode is enabled by calling {@code withLogLevel}.
     *
     * @param logger the logger
     * @return {@code this}
     */
    public DynatraceOpenKitBuilder withLogger(Logger logger) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Defines the version of the application. The value is only set if it is neither null nor empty.
     *
     * @param applicationVersion the application version
     * @return {@code this}
     */
    public DynatraceOpenKitBuilder withApplicationVersion(String applicationVersion) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the trust manager. Overrides the default trust manager which is {@code SSLStrictTrustManager} by default-
     *
     * @param trustManager trust manager implementation
     * @return {@code this}
     */
    public DynatraceOpenKitBuilder withTrustManager(SSLTrustManager trustManager) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the operating system information. The value is only set if it is neither null nor empty.
     *
     * @param operatingSystem the operating system
     * @return {@code this}
     */
    public DynatraceOpenKitBuilder withOperatingSystem(String operatingSystem) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the manufacturer information. The value is only set if it is neither null nor empty.
     *
     * @param manufacturer the manufacturer
     * @return {@code this}
     */
    public DynatraceOpenKitBuilder withManufacturer(String manufacturer) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the model id. The value is only set if it is neither null nor empty.
     *
     * @param modelID the model id
     * @return {@code this}
     */
    public DynatraceOpenKitBuilder withModelID(String modelID) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the maximum beacon record age of beacon data in cache.
     *
     * @param maxRecordAgeInMilliseconds The maximum beacon record age in milliseconds, or unbounded if negative.
     * @return {@code this}
     */
    public DynatraceOpenKitBuilder withBeaconCacheMaxRecordAge(long maxRecordAgeInMilliseconds) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the lower memory boundary of the beacon cache.
     *
     * <p>
     * When this is set to a positive value the memory based eviction strategy clears the collected data,
     * until the data size in the cache falls below the configured limit.
     * </p>
     *
     * @param lowerMemoryBoundaryInBytes The lower boundary of the beacon cache or negative if unlimited.
     * @return {@code this}
     */
    public DynatraceOpenKitBuilder withBeaconCacheLowerMemoryBoundary(long lowerMemoryBoundaryInBytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the upper memory boundary of the beacon cache.
     *
     * <p>
     * When this is set to a positive value the memory based eviction strategy starts to clear
     * data from the beacon cache when the cache size exceeds this setting.
     * </p>
     *
     * @param upperMemoryBoundaryInBytes The lower boundary of the beacon cache or negative if unlimited.
     * @return {@code this}
     */
    public DynatraceOpenKitBuilder withBeaconCacheUpperMemoryBoundary(long upperMemoryBoundaryInBytes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the data collection level.
     *
     * Depending on the chosen level the amount and granularity of data sent is controlled.<br>
     * {@code Off (0)} - no data collected<br>
     * {@code PERFORMANCE (1)} - only performance related data is collected<br>
     * {@code USER_BEHAVIOR (2)} - all available RUM data including performance related data is collected<br>
     *
     * Default value: {@code USER_BEHAVIOR}
     *
     * @param dataCollectionLevel Data collection level to apply.
     * @return {@code this}
     */
    public DynatraceOpenKitBuilder withDataCollectionLevel(DataCollectionLevel dataCollectionLevel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets the flag if crash reporting is enabled
     *
     * {@code OFF (0)} - Crashes are not sent to the server<br>
     * {@code OPT_OUT_CRASHES (1)} - Crashes are not sent to the server<br>
     * {@code OPT_IN_CRASHES (2)} - Crashes are sent to the server<br>
     *
     * Default value: {@code OPT_IN_CRASHES}
     *
     * @param crashReportLevel Flag if crash reporting is enabled
     * @return {@code this}
     */
    public DynatraceOpenKitBuilder withCrashReportingLevel(CrashReportingLevel crashReportLevel) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets a custom {@link HttpRequestInterceptor}
     *
     * @param httpRequestInterceptor Interceptor for intercepting requests to Dynatrace backends.
     * @return {@code this}
     */
    public DynatraceOpenKitBuilder withHttpRequestInterceptor(HttpRequestInterceptor httpRequestInterceptor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Sets a custom {@link HttpResponseInterceptor}
     *
     * @param httpResponseInterceptor Interceptor for intercepting responses received from Dynatrace backends.
     * @return {@code this}
     */
    public DynatraceOpenKitBuilder withHttpResponseInterceptor(HttpResponseInterceptor httpResponseInterceptor) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Builds a new {@code OpenKit} instance
     *
     * @return returns an {@code OpenKit} instance
     */
    public OpenKit build() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a string identifying the OpenKit type that gets created by this builder.
     *
     * <p>
     *     The only real purpose is for logging reasons.
     * </p>
     *
     * @return Some identification string identifying the OpenKit's type.
     */
    public String getOpenKitType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the application id for which the OpenKit reports data.
     *
     * @return Application id for which data will be reported.
     */
    public String getApplicationID() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the default server ID to communicate with.
     *
     * @return Default server id to communicate with.
     */
    public int getDefaultServerID() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the application version that has been set with {@link #withApplicationVersion(String)}.
     *
     * @return Previously set application version or {@link OpenKitConstants#DEFAULT_APPLICATION_VERSION} if none
     *         has been set.
     */
    public String getApplicationVersion() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the operating system that has been set with {@link #withOperatingSystem(String)}.
     *
     * @return Previously set operating system or {@link OpenKitConstants#DEFAULT_OPERATING_SYSTEM} if none
     *         has been set.
     */
    public String getOperatingSystem() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the manufacturer that has been set with {@link #withManufacturer(String)}.
     *
     * @return Previously set manufacturer or {@link OpenKitConstants#DEFAULT_MANUFACTURER} if none
     *         has been set.
     */
    public String getManufacturer() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the model identifier that has been set with {@link #withModelID(String)}.
     *
     * @return Previously set model ID or {@link OpenKitConstants#DEFAULT_MODEL_ID} if none
     *         has been set.
     */
    public String getModelID() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the endpoint URL that has been set in the constructor.
     *
     * <p>
     *     The endpoint URL is used to send beacon data to.
     * </p>
     *
     * @return Endpoint URL that has been configured in constructor.
     */
    public String getEndpointURL() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get this device identifier that has been set in the constructor.
     *
     * <p>
     *     The device identifier is a unique numeric value that identifies this device or installation.
     *     The user of the OpenKit library is responsible for providing a unique value per device/installation,
     *     which stays consistent per device/installation.
     * </p>
     *
     * @return Device identifier set in the constructor.
     */
    public long getDeviceID() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the {@link #getDeviceID() device identifier} in the representation before it was hashed (in case the
     * original device ID was a non numeric string).
     *
     * @return Device identifier in the representation as it was originally passed to the constructor.
     */
    public String getOrigDeviceID() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the SSL trust manager that has been set with {@link #withTrustManager(SSLTrustManager)}.
     *
     * <p>
     *     {@link SSLTrustManager} implementation are responsible for checking the X509 certificate chain
     *     and rejecting untrusted/invalid certificates.
     *     The default implementation rejects every untrusted/invalid (including self-signed) certificate.
     * </p>
     *
     * @return Previously set SSL trust manager or a default implementation.
     */
    public SSLTrustManager getTrustManager() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the maximum beacon cache record age that has been set with {@link #withBeaconCacheMaxRecordAge(long)}.
     *
     * @return Previously set maximum beacon cache record age or
     *         {@link ConfigurationDefaults#DEFAULT_MAX_RECORD_AGE_IN_MILLIS} if none has been set.
     */
    public long getBeaconCacheMaxRecordAge() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the beacon cache lower memory boundary that has been set with
     * {@link #withBeaconCacheLowerMemoryBoundary(long)}.
     *
     * @return Previously set lower memory boundary or
     *         {@link ConfigurationDefaults#DEFAULT_LOWER_MEMORY_BOUNDARY_IN_BYTES} if none has been set.
     */
    public long getBeaconCacheLowerMemoryBoundary() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the beacon cache upper memory boundary that has been set with
     * {@link #withBeaconCacheUpperMemoryBoundary(long)}.
     *
     * @return Previously set upper memory boundary or
     *         {@link ConfigurationDefaults#DEFAULT_UPPER_MEMORY_BOUNDARY_IN_BYTES} if none has been set.
     */
    public long getBeaconCacheUpperMemoryBoundary() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get data collection level that has been set with {@link #withDataCollectionLevel(DataCollectionLevel)}.
     *
     * @return Previously set data collection level or {@link ConfigurationDefaults#DEFAULT_DATA_COLLECTION_LEVEL}
     *         if nothing has been set.
     */
    public DataCollectionLevel getDataCollectionLevel() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get crash reporting level that has been set with {@link #withCrashReportingLevel(CrashReportingLevel)}.
     *
     * @return Previously set crash reporting level or {@link ConfigurationDefaults#DEFAULT_CRASH_REPORTING_LEVEL}
     *         if nothing has been set.
     */
    public CrashReportingLevel getCrashReportLevel() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get {@link HttpRequestInterceptor} that has been set with {@link #withHttpRequestInterceptor(HttpRequestInterceptor)}.
     *
     * @return Previously set HTTP request interceptor or {@link NullHttpRequestInterceptor#INSTANCE}
     *         if nothing has been set.
     */
    public HttpRequestInterceptor getHttpRequestInterceptor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get {@link HttpResponseInterceptor} that has been set with {@link #withHttpResponseInterceptor(HttpResponseInterceptor)}.
     *
     * @return Previously set HTTP response interceptor or {@link NullHttpResponseInterceptor#INSTANCE}
     *         if nothing has been set.
     */
    public HttpResponseInterceptor getHttpResponseInterceptor() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get {@link Logger} that has been set with {@link #withLogger(Logger)}.
     *
     * @return Previously set logger or {@link DefaultLogger} if none has been set.
     */
    public Logger getLogger() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static long deviceIdFromString(String deviceId) {
        if (deviceId != null) {
            deviceId = deviceId.trim();
        }
        try {
            return Long.parseLong(deviceId);
        } catch (NumberFormatException nex) {
            // given ID is not a valid number, calculate a corresponding hash
            return StringUtil.to64BitHash(deviceId);
        }
    }
}
