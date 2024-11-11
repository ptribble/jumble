/*
 * SPDX-License-Identifier: CDDL-1.0
 *
 * This file and its contents are supplied under the terms of the
 * Common Development and Distribution License ("CDDL"), version 1.0.
 * You may only use this file in accordance with the terms of version
 * 1.0 of the CDDL.
 *
 * A full copy of the text of the CDDL should have accompanied this
 * source. A copy of the CDDL is also available via the Internet at
 * http://www.illumos.org/license/CDDL.
 *
 * Copyright (C) 2004-2024 Peter C. Tribble
 */

package uk.co.petertribble.jumble;

import java.util.HashMap;
import java.util.Map;
import java.io.File;

/**
 * Convenience routines.
 * @author Peter Tribble
 * @version 1.3
 *
 */
public final class JumbleUtils {

    /**
     * This class cannot be instantiated, or subclassed.
     */
    private JumbleUtils() {
    }

    /**
     * Reads a File and creates a property Map.
     * The lines in the File are broken up into a key
     * and value, with the key being anything to the left of the first
     * '=' character and the value anything that is left over. If no '='
     * character is present, that line is skipped.
     *
     * @param f The <code>File</code> to be read.
     *
     * @return A <code>Map</code> containing the keys and values
     * specified by the input <code>String</code>
     */
    public static Map<String, String> fileToPropMap(File f) {
	return stringToPropMap(JumbleFile.getStringContents(f));
    }

    /**
     * Converts a <code>String</code> to a property Map. The
     * <code>String</code> is first broken up into lines.
     * Then the resulting lines are broken up into a key
     * and value, with the key being anything to the left of the first
     * '=' character and the value anything that is left over. If no '='
     * character is present, that <code>String</code> is skipped.
     *
     * @param s The <code>String</code> to be broken up.
     *
     * @return A <code>Map</code> containing the keys and values
     * specified by the input <code>String</code>
     */
    public static Map<String, String> stringToPropMap(String s) {
	return stringToPropMap(s, "\n");
    }

    /**
     * Converts a <code>String</code> to a property Map. The
     * <code>String</code> is first broken up using the specified delimiter.
     * Then the resulting <code>String</code>s are broken up into a key
     * and value, with the key being anything to the left of the first
     * '=' character and the value anything that is left over. If no '='
     * character is present, that <code>String</code> is skipped.
     *
     * @param s The <code>String</code> to be broken up.
     * @param delim The delimiter around which the <code>String</code>
     * will be broken up
     *
     * @return A <code>Map</code> containing the keys and values
     * specified by the input <code>String</code>
     */
    public static Map<String, String> stringToPropMap(String s, String delim) {
	Map<String, String> m = new HashMap<>();
	for (String ss : s.split(delim)) {
	    int i = ss.indexOf('=');
	    if (i > -1) {
		m.put(ss.substring(0, i), ss.substring(i + 1));
	    }
	}
	return m;
    }
}
