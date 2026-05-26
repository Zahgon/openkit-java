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

import com.dynatrace.openkit.core.objects.SessionImpl;
import com.dynatrace.openkit.protocol.StatusResponse;
import java.util.List;

/**
 * In this state open sessions are finished. After that all sessions are sent to the server.
 * <p>
 *     Transition to:
 *     <ul>
 *         <li>{@link BeaconSendingTerminalState}</li>
 *     </ul>
 * </p>
 */
class BeaconSendingFlushSessionsState extends AbstractBeaconSendingState {

    BeaconSendingFlushSessionsState() {
        super(false);
    }

    @Override
    void doExecute(BeaconSendingContext context) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    AbstractBeaconSendingState getShutdownState() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
