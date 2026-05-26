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
package com.dynatrace.openkit.core.communication;

import com.dynatrace.openkit.protocol.StatusResponse;
import java.util.concurrent.TimeUnit;

/**
 * Initial state for beacon sending.
 *
 * <p>
 * The initial state is used to retrieve the configuration from the server and update the configuration.
 * </p>
 *
 * <p>
 * Transition to:
 * <ul>
 * <li>{@link BeaconSendingTerminalState} upon shutdown request</li>
 * <li>{@link BeaconSendingCaptureOnState} if initial status request succeeded and capturing is enabled.</li>
 * <li>{@link BeaconSendingCaptureOffState} if initial status request succeeded and capturing is disabled.</li>
 * </ul>
 * </p>
 */
class BeaconSendingInitState extends AbstractBeaconSendingState {

    /**
     * Times to use as delay between consecutive re-executions of this state, when no state transition is performed.
     */
    static final long[] REINIT_DELAY_MILLISECONDS = { TimeUnit.MINUTES.toMillis(1), TimeUnit.MINUTES.toMillis(5), TimeUnit.MINUTES.toMillis(15), TimeUnit.HOURS.toMillis(1), TimeUnit.HOURS.toMillis(2) };

    /**
     * Maximum number of retries
     */
    private static final int MAX_INITIAL_STATUS_REQUEST_RETRIES = 5;

    static final long INITIAL_RETRY_SLEEP_TIME_MILLISECONDS = TimeUnit.SECONDS.toMillis(1);

    /**
     * Index to re-initialize delays.
     */
    private int reinitializeDelayIndex = 0;

    BeaconSendingInitState() {
        super(false);
    }

    @Override
    void doExecute(BeaconSendingContext context) throws InterruptedException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    AbstractBeaconSendingState getShutdownState() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void onInterrupted(BeaconSendingContext context) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Execute status requests, until a successful response was received or shutdown was requested.
     *
     * @param context The state's context
     * @return The last received status response, which might be erroneous if shutdown has been requested.
     *
     * @throws InterruptedException Thrown if the current thread has been interrupted.
     */
    private StatusResponse executeStatusRequest(BeaconSendingContext context) throws InterruptedException {
        StatusResponse statusResponse;
        while (true) {
            long currentTimestamp = context.getCurrentTimestamp();
            context.setLastOpenSessionBeaconSendTime(currentTimestamp);
            context.setLastStatusCheckTime(currentTimestamp);
            statusResponse = BeaconSendingRequestUtil.sendStatusRequest(context, MAX_INITIAL_STATUS_REQUEST_RETRIES, INITIAL_RETRY_SLEEP_TIME_MILLISECONDS);
            if (context.isShutdownRequested() || BeaconSendingResponseUtil.isSuccessfulResponse(statusResponse)) {
                // shutdown was requested or a successful status response was received
                break;
            }
            long sleepTime = REINIT_DELAY_MILLISECONDS[reinitializeDelayIndex];
            if (BeaconSendingResponseUtil.isTooManyRequestsResponse(statusResponse)) {
                // in case of too many requests the server might send us a retry-after
                sleepTime = statusResponse.getRetryAfterInMilliseconds();
                // also temporarily disable capturing to avoid further server overloading
                context.disableCaptureAndClear();
            }
            // status request needs to be sent again after some delay
            context.sleep(sleepTime);
            // ensure no out of bounds
            reinitializeDelayIndex = Math.min(reinitializeDelayIndex + 1, REINIT_DELAY_MILLISECONDS.length - 1);
        }
        return statusResponse;
    }
}
