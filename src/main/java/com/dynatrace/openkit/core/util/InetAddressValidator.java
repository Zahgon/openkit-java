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
package com.dynatrace.openkit.core.util;

import java.util.regex.Pattern;

/**
 * This class provides static methods to check for valid Inet addresses in IPv4, IPv6 or
 * mixed notation.
 */
public class InetAddressValidator {

    private static final Pattern IPV4_PATTERN = Pattern.compile(// start of string
    "^" + // first block - a number from 0-255
    "(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)" + // three more blocks - numbers from 0-255 - each prepended by a point character '.'
    "(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}" + // end of string
    "$");

    private static final Pattern IPV6_STD_PATTERN = Pattern.compile(// start of string
    "^" + // 7 blocks of a 1 to 4 digit hex number followed by double colon ':'
    "(?:[0-9a-fA-F]{1,4}:){7}" + // one more block of a 1 to 4 digit hex number
    "[0-9a-fA-F]{1,4}" + // end of string
    "$");

    private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern.compile(// start of string
    "^" + // 1st group
    "(" + // at least one block of a 1 to 4 digit hex number
    "(?:[0-9A-Fa-f]{1,4}" + // optional further blocks, any number
    "(?::[0-9A-Fa-f]{1,4})*)?" + ")" + // in the middle of the expression the two occurences of ':' are neccessary
    "::" + // 2nd group
    "(" + // at least one block of a 1 to 4 digit hex number
    "(?:[0-9A-Fa-f]{1,4}" + // optional further blocks, any number
    "(?::[0-9A-Fa-f]{1,4})*)?" + ")" + // end of string
    "$");

    //this regex checks the ipv6 uncompressed part of a ipv6 mixed address
    private static final Pattern IPV6_MIXED_COMPRESSED_REGEX = Pattern.compile(// start of string
    "^" + // 1st group
    "(" + // at least one block of a 1 to 4 digit hex number
    "(?:[0-9A-Fa-f]{1,4}" + // optional further blocks, any number
    "(?::[0-9A-Fa-f]{1,4})*)?" + ")" + // in the middle of the expression the two occurences of ':' are neccessary
    "::" + // 2nd group
    "(" + // at least one block of a 1 to 4 digit hex number followed by a ':' character
    "(?:[0-9A-Fa-f]{1,4}:" + // optional further blocks, any number, all succeeded by ':' character
    "(?:[0-9A-Fa-f]{1,4}:)*)?" + ")" + // end of string
    "$");

    //this regex checks the ipv6 uncompressed part of a ipv6 mixed address
    private static final Pattern IPV6_MIXED_UNCOMPRESSED_REGEX = Pattern.compile(// start of string
    "^" + // 6 blocks of a 1 to 4 digit hex number followed by double colon ':'
    "(?:[0-9a-fA-F]{1,4}:){6}" + // end of string
    "$");

    /**
     * Check if <code>input</code> is a valid IPv4 address
     * <p>
     * <p>
     * The format is 'xxx.xxx.xxx.xxx'. Four blocks of integer numbers ranging from 0 to 255
     * are required. Letters are not allowed.
     * </p>
     *
     * @param input ip-address to check
     * @return true if <code>input</code> is in correct IPv4 notation.
     */
    public static boolean isIPv4Address(final String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check if the given address is a valid IPv6 address in the standard format
     * <p>
     * <p>
     * The format is 'xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx'. Eight blocks of hexadecimal digits
     * are required.
     * </p>
     *
     * @param input ip-address to check
     * @return true if <code>input</code> is in correct IPv6 notation.
     */
    public static boolean isIPv6StdAddress(final String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check if the given address is a valid IPv6 address in the hex-compressed notation
     * <p>
     * <p>
     * The format is 'xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx:xxxx'. If all digits in a block are '0'
     * the block can be left empty.
     * </p>
     *
     * @param input ip-address to check
     * @return true if <code>input</code> is in correct IPv6 (hex-compressed) notation.
     */
    public static boolean isIPv6HexCompressedAddress(final String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check if <code>input</code> is a IPv6 address.
     * <p>
     * Possible notations for valid IPv6 are:
     * - Standard IPv6 address
     * - Hex-compressed IPv6 address
     * - Link-local IPv6 address
     * - IPv4-mapped-to-IPV6 address
     * - IPv6 mixed address
     * </p>
     *
     * @param input ip-address to check
     * @return true if <code>input</code> is in correct IPv6 notation.
     */
    public static boolean isIPv6Address(final String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check if the given address is a valid IPv6 address in the mixed-standard or mixed-compressed notation.
     *
     * IPV6 Mixed mode consists of two parts, the first 96 bits (up to 6 blocks of 4 hex digits) are IPv6
     * the IPV6 part can be either compressed or uncompressed
     * the second block is a full IPv4 address
     * e.g. '0:0:0:0:0:0:172.12.55.18'
     * @param input ip-address to check
     * @return true if <code>input</code> is in correct IPv6 (mixed-standard or mixed-compressed) notation.
     */
    public static boolean isIPv6MixedAddress(final String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check if <code>input</code> is an IPv4 address mapped into a IPv6 address. These are
     * starting with "::ffff:" followed by the IPv4 address in a dot-seperated notation.
     * <p>
     * The format is '::ffff:d.d.d.d'
     * </p>
     *
     * @param input ip-address to check
     * @return true if <code>input</code> is in correct IPv6 notation containing an IPv4 address
     */
    public static boolean isIPv6IPv4MappedAddress(final String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check if <code>input</code> is a link local IPv6 address starting with "fe80:" and containing
     * a zone index with "%xxx". The zone index will not be checked.
     *
     * @param input ip-address to check
     * @return true if address part of <code>input</code> is in correct IPv6 notation.
     */
    public static boolean isLinkLocalIPv6WithZoneIndex(String input) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Check if <code>input</code> is a valid IPv4 or IPv6 address.
     *
     * @param ipAddress ip-address to check
     * @return <code>true</code> if <code>ipAddress</code> is a valid ip-address
     */
    public static boolean isValidIP(String ipAddress) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
