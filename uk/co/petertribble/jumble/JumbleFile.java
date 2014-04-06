/*
 * JUMBLE - a collection of miscellaneous non-graphical
 * functions used by  applications
 *
 * Copyright (C) 2004-2011 Peter C. Tribble
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
 * @version 1.2
 *
 */
public class JumbleFile {

    private File file;
    private String message;
    private static final String nl = System.getProperty("line.separator");

    /**
     * Read a file, specified by name
     *
     * @param filename The name of the file to be read
     */
    public JumbleFile(String filename) {
	this(new File(filename));
    }

    /**
     * Read a file, specified by a <code>File</code> object
     *
     * @param file The <code>File</code> to be read
     */
    public JumbleFile(File file) {
	this.file = file;
    }

    /**
     * Gets the contents of the file. This returns an <code>Object</code>
     * which is exactly the <code>byte</code> array returned by the
     * {@link #getByteContents()} method
     *
     * @return The contents of a file as an <code>Object</code>
     */
    public Object contents() {
	return getByteContents();
    }

    /**
     * Gets the contents of the file, explicitly as a <code>byte</code> array
     *
     * @return The contents of a file as an array of <code>byte</code>s
     */
    public byte[] getByteContents() {
	byte[] b;
	try {
	    long s = file.length();
	    b = new byte[(int)s];
	    FileInputStream fi = new FileInputStream(file);
	    fi.read(b);
	    fi.close();
	} catch (IOException ioe) {
	    b = null;
	}
	return b;
    }

    /**
     * Gets the contents of the file as a <code>String</code>
     *
     * @return The contents of the file as a <code>String</code>
     */
    public String getStringContents() {
	return JumbleUtils.byteToString(getByteContents());
    }

    /**
     * Gets the contents of the file as an arrays of <code>String</code>
     * representing its lines.
     *
     * @return The contents of the file as a <code>String</code>
     */
    public String[] getLines() {
	return getStringContents().split(nl);
    }

    /**
     * Write to a file.
     *
     * @param data The data to be written, which must either be a
     * <code>String</code> or an array of <code>byte</code>s
     *
     * @return A boolean value indicating whether the write succeeded. Use
     * {@link #getMessage()} to determine why the write failed.
     */
    public boolean put(Object data) {
	boolean successful = false;
	if (data.getClass().equals(String.class)) {
	    try {
		FileWriter out = new FileWriter(file);
		out.write((String) data);
		out.close();
		successful = true;
	    } catch (IOException ioe) {
		message = "File save failed: "+ioe.getMessage();
	    }
	} else {
	    try {
		FileOutputStream out = new FileOutputStream(file);
		out.write((byte []) data);
		out.close();
		successful = true;
	    } catch (IOException ioe) {
		message = "File save failed: "+ioe.getMessage();
	    }
	}
	return successful;
    }

    /**
     * Get a status message, if any.
     *
     * @return A message associated with the last file operation
     */
    public String getMessage() {
	return message;
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
     * Static utility method to read a file into a <code>byte</code> array
     *
     * @param f A <code>File</code> to be read.
     *
     * @return The contents of a file as an array of <code>byte</code>s
     */
    public static byte[] getByteContents(File f) {
	byte[] b = null;
	try {
	    long s = f.length();
	    b = new byte[(int)s];
	    FileInputStream fi = new FileInputStream(f);
	    fi.read(b);
	    fi.close();
	} catch (IOException ioe) { }
	return b;
    }

    /**
     * Static utility method to read a file into a <code>String</code>
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
	return getStringContents(f).split(nl);
    }

    /**
     * Static utility method to write to a file.
     *
     * @param f A <code>File</code> to be written to.
     * @param data The data to be written, which must either be a
     * <code>String</code> or an array of <code>byte</code>s
     *
     * @return A boolean value indicating whether the write succeeded.
     */
    public static boolean put(File f, Object data) {
	boolean successful = false;
	if (data.getClass().equals(String.class)) {
	    try {
		FileWriter out = new FileWriter(f);
		out.write((String) data);
		out.close();
		successful = true;
	    } catch (IOException ioe) { }
	} else {
	    try {
		FileOutputStream out = new FileOutputStream(f);
		out.write((byte []) data);
		out.close();
		successful = true;
	    } catch (IOException ioe) { }
	}
	return successful;
    }
}
