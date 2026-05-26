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
package com.dynatrace.openkit.util.json.lexer;

import com.dynatrace.openkit.util.json.constants.JSONLiterals;

/**
 * Container class representing a token.
 */
public final class JSONToken {

    /**
     * Token's type.
     */
    public enum TokenType {

        // numeric value, the value is the number text
        VALUE_NUMBER,
        // string value, value does not include leading and trailing " characters
        VALUE_STRING,
        // boolean literal, value is either true or false
        LITERAL_BOOLEAN,
        // null literal, value will be null (as string)
        LITERAL_NULL,
        // {
        LEFT_BRACE,
        // }
        RIGHT_BRACE,
        // [
        LEFT_SQUARE_BRACKET,
        // ]
        RIGHT_SQUARE_BRACKET,
        // ,
        COMMA,
        // :
        COLON
    }

    /**
     * {@link JSONToken} to be used for boolean {@code true} literal
     */
    static final JSONToken BOOLEAN_TRUE_TOKEN = new JSONToken(TokenType.LITERAL_BOOLEAN, JSONLiterals.BOOLEAN_TRUE_LITERAL);

    /**
     * {@link JSONToken} to be used for boolean {@code false} literal
     */
    static final JSONToken BOOLEAN_FALSE_TOKEN = new JSONToken(TokenType.LITERAL_BOOLEAN, JSONLiterals.BOOLEAN_FALSE_LITERAL);

    /**
     * {@link JSONToken} to be used for null literal
     */
    static final JSONToken NULL_TOKEN = new JSONToken(TokenType.LITERAL_NULL, JSONLiterals.NULL_LITERAL);

    /**
     * {@link JSONToken} to be used for left brace
     */
    static final JSONToken LEFT_BRACE_TOKEN = new JSONToken(TokenType.LEFT_BRACE);

    /**
     * {@link JSONToken} to be used for right brace
     */
    static final JSONToken RIGHT_BRACE_TOKEN = new JSONToken(TokenType.RIGHT_BRACE);

    /**
     * {@link JSONToken} to be used for left square bracket
     */
    static final JSONToken LEFT_SQUARE_BRACKET_TOKEN = new JSONToken(TokenType.LEFT_SQUARE_BRACKET);

    /**
     * {@link JSONToken} to be used for right square bracket
     */
    static final JSONToken RIGHT_SQUARE_BRACKET_TOKEN = new JSONToken(TokenType.RIGHT_SQUARE_BRACKET);

    /**
     * {@link JSONToken} to be used for comma
     */
    static final JSONToken COMMA_TOKEN = new JSONToken(TokenType.COMMA);

    /**
     * {@link JSONToken} to be used for colon
     */
    static final JSONToken COLON_TOKEN = new JSONToken(TokenType.COLON);

    /**
     * Type of this token
     */
    private final TokenType tokenType;

    /**
     * Token value for primitive tokens
     */
    private final String value;

    /**
     * Construct the token with type only and set value to {@code null}.
     *
     * @param tokenType Type of this token.
     */
    private JSONToken(TokenType tokenType) {
        this(tokenType, null);
    }

    /**
     * Construct the token with type and value.
     *
     * <p>
     *     Instead of using this CTOR either use one of the following
     *     <ul>
     *         <li>the predefined instances</li>
     *         <li>{@link #createStringToken(String)}</li>
     *     </ul>
     * </p>
     *
     * @param tokenType Type of this token.
     * @param value Value of this token, if it has a value.
     */
    private JSONToken(TokenType tokenType, String value) {
        this.tokenType = tokenType;
        this.value = value;
    }

    /**
     * Create a new {@link JSONToken} with type {@link TokenType#VALUE_STRING} and given value.
     *
     * @param stringValue The value to be used for the token
     * @return The newly created token
     */
    static JSONToken createStringToken(String stringValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Create a new {@link JSONToken} with type {@link TokenType#VALUE_NUMBER} and given value.
     *
     * @param numericValue The value to be used for the token
     * @return The newly created token
     */
    static JSONToken createNumberToken(String numericValue) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get the type of the token.
     *
     * @return Token's type.
     */
    public TokenType getTokenType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get token value as string.
     *
     * <p>
     *     This method is only relevant for number, boolean and string tokens.
     * </p>
     *
     * @return Token value as string.
     */
    public String getValue() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Get string representation of given {@link TokenType}.
     *
     * @param tokenType The {@link TokenType} for which to obtain string representation.
     * @return String representation of tokenType
     */
    static String tokenTypeToString(TokenType tokenType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
