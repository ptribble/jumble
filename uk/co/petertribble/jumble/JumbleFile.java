/*
 * JUMBLE - a collection of miscellaneous non-graphical
 * functions used by applications
 *
 * Copyright (C) 2004-2024 Peter C. Tribble
 *
 * You may contact the author by email: peter.tribble@gmail.com
 */

package uk.co.petertribble.jumble;

import java.io.*;

/**
 *
 * Convenience routines for accessing files. Allows for reading a file into a
 * <code>String</code> or arrays of <code>byte</code>s
 *
 * @author Peter Tribble
 * @version 2.0
 *
 */
public final class JumbleFile {

    private static final String NLSEP = System.getProperty("line.separator");

    /**
     * This class cannot be instantiated, or subclassed.
     */
    private JumbleFile() {
    }

    /**
     * Static utility method to read a file.
     *
     * @param f A <code>File</code> to be read.
     *
     * @return The contents of a file as an <code>Object</code>
     */
    public static Object contents(File f) {
	return getByteContents(f);
    }

    /**
     * Static utility method to read a file into a <code>byte</code> array.
     *
     * @param f A <code>File</code> to be read.
     *
     * @return The contents of a file as an array of <code>byte</code>s
     */
    public static byte[] getByteContents(File f) {
	byte[] b = null;
	try {
	    long s = f.length();
	    b = new byte[(int) s];
	    FileInputStream fi = new FileInputStream(f);
	    fi.read(b);
	    fi.close();
	} catch (IOException ioe) { }
	return b;
    }

    /**
     * Static utility method to read a file into a <code>String</code>.
     *
     * @param f A <code>File</code> to be read.
     *
     * @return The contents of the file as a <code>String</code>
     */
    public static String getStringContents(File f) {
	return JumbleUtils.byteToString(getByteContents(f));
    }

    /**
     * Static utility method to read a file into an array of
     * <code>String</code> representing its lines.
     *
     * @param f A <code>File</code> to be read.
     *
     * @return The lines of the file as a <code>String</code>
     */
    public static String[] getLines(File f) {
	return getStringContents(f).split(NLSEP);
    }
}
