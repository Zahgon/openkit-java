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
package com.dynatrace.openkit.core;

import com.dynatrace.openkit.api.Logger;
import com.dynatrace.openkit.core.objects.SessionImpl;
import com.dynatrace.openkit.core.objects.SessionProxyImpl;
import java.util.concurrent.TimeUnit;

/**
 * The SessionWatchdog is responsible to perform certain actions for a session at a specific point in time.
 *
 * Currently following actions are performed:
 * <ul>
 *     <li>Sessions which could not be closed after session splitting will be closed after a certain grace period.</li>
 *     <li>Session proxies which require splitting after a maximum session duration or by idle timeout</li>
 * </ul>
 */
public class SessionWatchdog {

    private static final String THREAD_NAME = SessionWatchdog.class.getSimpleName();

    private static final long SHUTDOWN_TIMEOUT = TimeUnit.SECONDS.toMillis(2);

    /**
     * Logger for tracing/reporting messages
     */
    private final Logger logger;

    /**
     * The thread checking split open sessions and sessions which are to be split after idle/max timeout.
     */
    private Thread sessionWatchdogThread;

    /**
     * Context holding the split not closed sessions and sessions for splitting after idle/max timeout
     */
    private final SessionWatchdogContext context;

    public SessionWatchdog(Logger logger, SessionWatchdogContext context) {
        this.logger = logger;
        this.context = context;
    }

    public synchronized void initialize() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public synchronized void shutdown() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Tries to close/end the given session or enqueues it for closing if closing was not possible
     *
     * @param session the session to be closed or enqueued for closing.
     * @param closeGracePeriodInMillis the grace period after which the session is to be closed for good.
     */
    public void closeOrEnqueueForClosing(SessionImpl session, int closeGracePeriodInMillis) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Removes the given session for auto-closing from this watchdog
     * @param session the session to be removed.
     */
    public void dequeueFromClosing(SessionImpl session) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Adds the given session proxy so that it will be automatically split the underlying session when the idle timeout
     * or the max session time is reached.
     *
     * @param sessionProxy the session proxy to be added.
     */
    public void addToSplitByTimeout(SessionProxyImpl sessionProxy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Removes the given session proxy from automatically splitting it after idle or session max time expired.
     *
     * @param sessionProxy the session proxy to be removed.
     */
    public void removeFromSplitByTimeout(SessionProxyImpl sessionProxy) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
