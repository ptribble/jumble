/*
 * JUMBLE - a collection of miscellaneous non-graphical
 * functions used by applications
 *
 * Copyright (C) 2004-2020 Peter C. Tribble
 *
 * You may contact the author by email: peter.tribble@gmail.com
 *
 */

package uk.co.petertribble.jumble;

import java.util.*;
import java.io.*;

/**
 * Convenience routines.
 * @author Peter Tribble
 * @version 1.3
 *
 */
public class JumbleUtils {

    /**
     * Sanitizes a file name, replacing undesirable characters with the
     * "_" character.
     *
     * @param s The filename to be sanitized
     *
     * @return The sanitized filename
     */
    public static String safeFileName(String s) {
	char c;
	int len = s.length();
	StringBuilder dest = new StringBuilder(len);
	for (int i = 0; i < len; i++) {
	    c = s.charAt(i);
	    if (c == ':' || c == '/' || c == ' ' || c == '>' || c == '<'
		|| c == ';' || c == '\\') {
		dest.append('_');
	    } else {
		dest.append(c);
	    }
	}
	return dest.toString();
    }

    /**
     * Reads a <code>String</code> out of a <code>byte</code> array.
     *
     * @param b An array of <code>byte</code>s to be converted to a
     * <code>String</code>
     *
     * @return The converted <code>String</code>
     */
    public static String byteToString(byte[] b) {
	String s = "";
	InputStreamReader isr = new InputStreamReader(
		new ByteArrayInputStream(b));
	try {
	    int l = b.length;
	    char[] cc = new char[l];
	    isr.read(cc, 0, l);
	    s = new String(cc);
	} catch (Exception e) { }
	return s;
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
    public static Map <String, String> stringToPropMap(String s, String delim) {
	Map <String, String> m = new HashMap <String, String> ();
	StringTokenizer st = new StringTokenizer(s, delim);
	while (st.hasMoreTokens()) {
	    String ss = st.nextToken();
	    int i = ss.indexOf("=");
	    if (i > -1) {
		m.put(ss.substring(0, i), ss.substring(i+1));
	    }
	}
	return m;
    }
}
