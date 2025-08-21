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
 * Copyright (C) 2004-2025 Peter C. Tribble
 */

package uk.co.petertribble.jumble;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

/**
 *
 * Convenience routines for accessing files. Allows for reading a file into a
 * <code>String</code> or a <code>List</code> thereof
 *
 * @author Peter Tribble
 * @version 4.0
 *
 */
public final class JumbleFile {

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
    public static Object contents(final File f) {
	return getByteContents(f);
    }

    /**
     * Static utility method to read a file into a <code>byte</code> array.
     * Returns null in the event of failure.
     *
     * @param f A <code>File</code> to be read.
     *
     * @return The contents of a file as an array of <code>byte</code>s
     */
    public static byte[] getByteContents(final File f) {
	byte[] b = null;
	try (FileInputStream fi = new FileInputStream(f)) {
	    long s = f.length();
	    b = new byte[(int) s];
	    fi.read(b);
	} catch (IOException ioe) { }
	return b;
    }

    /**
     * Static utility method to read a file into a <code>String</code>.
     *
     * @param dirname The directory containing filename.
     * @param filename The name of the file to be read.
     *
     * @return The contents of the file as a <code>String</code>
     */
    public static String getStringContents(final File dirname,
					   final String filename) {
	return getStringContents(new File(dirname, filename));
    }

    /**
     * Static utility method to read a file into a <code>String</code>.
     *
     * @param f A <code>File</code> to be read.
     *
     * @return The contents of the file as a <code>String</code>
     */
    public static String getStringContents(final File f) {
	try {
	    return Files.readString(f.toPath());
	} catch (IOException ioe) {
	    return "";
	}
    }

    /**
     * Static utility method to read a file into a <code>List</code> of
     * <code>String</code> representing its lines.
     *
     * @param dirname The directory containing filename.
     * @param filename The name of the file to be read.
     *
     * @return The lines of the file as a <code>List</code> of
     * <code>String</code>
     */
    public static List<String> readAllLines(final File dirname,
					    final String filename) {
	return readAllLines(new File(dirname, filename));
    }

    /**
     * Static utility method to read a file into a <code>List</code> of
     * <code>String</code> representing its lines.
     *
     * @param filename The name of the file to be read.
     *
     * @return The lines of the file as a <code>List</code> of
     * <code>String</code>
     */
    public static List<String> readAllLines(final String filename) {
	return readAllLines(new File(filename));
    }

    /**
     * Static utility method to read a file into a <code>List</code> of
     * <code>String</code> representing its lines.
     *
     * @param f A <code>File</code> to be read.
     *
     * @return The lines of the file as a <code>List</code> of
     * <code>String</code>
     */
    public static List<String> readAllLines(final File f) {
	try {
	    return Files.readAllLines(f.toPath());
	} catch (IOException ioe) {
	    return Collections.emptyList();
	}
    }
}
