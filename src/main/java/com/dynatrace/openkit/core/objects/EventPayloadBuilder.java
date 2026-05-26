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

import com.dynatrace.openkit.api.Logger;
import com.dynatrace.openkit.util.json.objects.JSONObjectValue;
import com.dynatrace.openkit.util.json.objects.JSONValue;
import java.util.*;
import static com.dynatrace.openkit.core.util.EventPayloadBuilderUtil.isItemContainingNonFiniteNumericValues;

public class EventPayloadBuilder {

    /**
     * {@link Logger} for tracing log message
     */
    private final Logger logger;

    /**
     * Map containing attributes for sendEvent API
     */
    private final Map<String, JSONValue> attributes;

    public EventPayloadBuilder(Logger logger, Map<String, JSONValue> attributes) {
        this.logger = logger;
        if (attributes == null) {
            this.attributes = new HashMap<>();
        } else {
            this.attributes = new HashMap<>(attributes);
        }
    }

    public EventPayloadBuilder addOverridableAttribute(String key, JSONValue value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public EventPayloadBuilder addNonOverridableAttribute(String key, JSONValue value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String build() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Removes reservered internal attributes from the provided attributes
     */
    public EventPayloadBuilder cleanReservedInternalAttributes() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks if an attribute is actually reserved for internal purpose
     * @param key Key to check
     * @return True means the key is in use for internal purpose
     */
    public static boolean isReservedForInternalAttributes(String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks if the attributes contain a non-finite value
     * @return True if non-finite values is within attributes
     */
    public boolean isEventPayloadContainingNonFiniteValues() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
