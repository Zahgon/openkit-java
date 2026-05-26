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

import com.dynatrace.openkit.util.json.constants.JSONLiterals;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;

/**
 * JSON value class representing a number value.
 *
 * <p>
 *     A number can either be a floating point number or an integer number.
 *     To avoid platform specific problems, all integer numbers can be up to signed 64 bits,
 *     and all floating point values are 64 bits.
 * </p>
 */
public class JSONNumberValue extends JSONValue {

    /**
     * Boolean flag indicating whether this is an integer value {@code true} or a floating point value {@code false}.
     */
    private final boolean isInteger;

    /**
     * The number stored as long value.
     */
    private final long longValue;

    /**
     * The number stored as double value.
     */
    private final double doubleValue;

    /**
     * Constructor initializing this {@link JSONNumberValue} instance with given long value.
     *
     * <p>
     *     Instead of using this constructor, use the {@link #fromLong(long)} factory method.
     * </p>
     *
     * @param longValue The value representing this instance.
     */
    private JSONNumberValue(long longValue) {
        this.isInteger = true;
        this.longValue = longValue;
        this.doubleValue = longValue;
    }

    /**
     * Constructor initializing this {@link JSONNumberValue} instance with given double value.
     *
     * <p>
     *     Instead of using this constructor, use the {@link #fromDouble(double)} factory method.
     * </p>
     *
     * @param doubleValue The value representing this instance.
     */
    private JSONNumberValue(double doubleValue) {
        this.isInteger = false;
        this.longValue = (long) doubleValue;
        this.doubleValue = doubleValue;
    }

    /**
     * Factory method for constructing a {@link JSONNumberValue} from a {@code long}.
     *
     * @param longValue The long value.
     *
     * @return Newly created {@link JSONNumberValue}
     */
    public static JSONNumberValue fromLong(long longValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Factory method for constructing a {@link JSONNumberValue} from a {@code double}.
     *
     * @param doubleValue The double value.
     *
     * @return Newly created {@link JSONNumberValue}
     */
    public static JSONNumberValue fromDouble(double doubleValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Factory method for constructing a {@link JSONNumberValue} from a number literal.
     *
     * @param literalValue The number literal, which might either be an integer value or a floating point value.
     *
     * @return {@code null} if {@code literalValue} is {@code null} or does not represent a number
     *      or a newly created {@link JSONNumberValue}.
     */
    public static JSONNumberValue fromNumberLiteral(String literalValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    void writeJSONString(JSONValueWriter writer, JSONOutputConfig config) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean isNumber() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a boolean indicating whether this {@link JSONNumberValue} represents an integer value or not.
     *
     * @return {@code true} if this instance represents an integer value, {@code false} otherwise.
     */
    public boolean isInteger() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a boolean indicating whether this {@link JSONNumberValue} represents a 32-bit integer or not.
     *
     * @return {@code true} if this instance represents an integer value, {@code false} otherwise.
     */
    public boolean isIntValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a 32-bit integer value.
     *
     * <p>
     *     If this instance is representing a double or long value, then the result is the value casted to an {@code int}.
     * </p>
     *
     * @return 32-bit integer value represented by this instance.
     */
    public int getIntValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a 64-bit integer value.
     *
     * <p>
     *     If this instance is representing a double, then the result is the value casted to a {@code long}.
     * </p>
     *
     * @return 64-bit integer value represented by this instance.
     */
    public long getLongValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a 32-bit floating point value.
     *
     * @return 32-bit floating point value represented by this instance.
     */
    public float getFloatValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get a 64-bit floating point value.
     *
     * @return 64-bit floating point value represented by this instance.
     */
    public double getDoubleValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Checks if the number is finite.
     *
     * @return True if number is finite.
     */
    public boolean isFinite() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
