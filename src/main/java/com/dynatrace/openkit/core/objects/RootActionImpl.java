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
package com.dynatrace.openkit.core.objects;

import com.dynatrace.openkit.api.Action;
import com.dynatrace.openkit.api.Logger;
import com.dynatrace.openkit.api.RootAction;
import com.dynatrace.openkit.protocol.Beacon;

/**
 * Actual implementation of the {@link RootAction} interface.
 */
public class RootActionImpl extends BaseActionImpl implements RootAction {

    /**
     * Constructor for constructing the root action class.
     *
     * @param logger The logger used to log information
     * @param parentSession The session, to which this root action belongs to
     * @param name The action's name
     * @param beacon The beacon for retrieving certain data and sending data
     */
    RootActionImpl(Logger logger, SessionImpl parentSession, String name, Beacon beacon) {
        super(logger, parentSession, name, beacon);
    }

    @Override
    public Action enterAction(String actionName) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected Action getParentAction() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
