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
package com.dynatrace.openkit.util.json.objects;

/**
 * Base class for all JSON value classes (e.g. string, number)
 */
public abstract class JSONValue {

    /**
     * Get a boolean indicating whether this instance represents a JSON null value or not.
     *
     * @return {@code true} for objects representing a null value, {@code false} otherwise.
     */
    public boolean isNull() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a boolean indicating whether this instance represents a JSON boolean value or not.
     *
     * @return {@code true} for objects representing a boolean value, {@code false} otherwise.
     */
    public boolean isBoolean() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a boolean indicating whether this instance represents a JSON numeric value or not.
     *
     * @return {@code true} for objects representing a numeric value, {@code false} otherwise.
     */
    public boolean isNumber() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a boolean indicating whether this instance represents a JSON string value or not.
     *
     * @return {@code true} for objects representing a string value, {@code false} otherwise.
     */
    public boolean isString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a boolean indicating whether this instance represents an JSON array value or not.
     *
     * @return {@code true} for objects representing an array value, {@code false} otherwise.
     */
    public boolean isArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a boolean indicating whether this instance represents an JSON object value or not.
     *
     * @return {@code true} for objects representing an object value, {@code false} otherwise.
     */
    public boolean isObject() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a string representation of the JSON value
     * @return json string
     */
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns a string representation of the JSON value but allows the option to pass a
     * configuration which changes the string output
     * @param config Configuration which changes the output
     * @return json string
     */
    public String toString(JSONOutputConfig config) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Writes the JSON string to a write which will be returned in the toString
     * @param writer JSONWriter
     */
    abstract void writeJSONString(JSONValueWriter writer, JSONOutputConfig config);
}
