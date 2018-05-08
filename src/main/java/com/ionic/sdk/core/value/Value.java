package com.ionic.sdk.core.value;

import java.util.Collection;

/**
 * Utilities for working with generic values.
 */
public final class Value {

    /**
     * Constructor.
     * http://checkstyle.sourceforge.net/config_design.html#FinalClass
     */
    private Value() {
    }

    /**
     * Check input object for data.
     *
     * @param value input to be checked
     * @return true iff [value is null, or value is empty (zero length)]
     */
    public static boolean isEmpty(final byte[] value) {
        return ((value == null) || (value.length == 0));
    }

    /**
     * Check input objects for any occurrence of a null value.
     *
     * @param objects objects to check for null values
     * @return true if any null is found in the input data
     */
    public static boolean hasNull(final Object... objects) {
        boolean hasNull = false;
        for (final Object object : objects) {
            hasNull |= (object == null);
        }
        return hasNull;
    }

    /**
     * Check input object for data.
     *
     * @param value input to be checked
     * @return true iff [value is null, or value is empty (zero length)]
     */
    public static boolean isEmpty(final String value) {
        return ((value == null) || (value.length() == 0));
    }

    /**
     * Allow specification of a default value, if no data found in original input.
     *
     * @param value        input to be checked
     * @param defaultValue value to substitute iff no data found in original input
     * @return value, iff it contains data, otherwise defaultValue
     */
    public static String defaultOnEmpty(final String value, final String defaultValue) {
        return (((value == null) || (value.length() == 0)) ? defaultValue : value);
    }

    /**
     * Check input objects for equality.
     *
     * @param left  input object
     * @param right input object
     * @return true iff the two objects are either (1) both null, or (2) both strings, and equal to each other
     */
    public static boolean isEqual(final String left, final String right) {
        return (left == null) ? (right == null) : (left.equals(right));
    }

    /**
     * Check input objects for equality.
     *
     * @param left  input object
     * @param right input object
     * @return true iff the two objects are either (1) both null, or (2) both strings, and equal to each other
     */
    public static boolean isEqualIgnoreCase(final String left, final String right) {
        return (left == null) ? (right == null) : (left.equalsIgnoreCase(right));
    }

    /**
     * Assemble input atoms into a string, with a defined separator between each.
     *
     * @param connector the token which should delimit each input atom
     * @param value     the first object to render
     * @param values    the objects to render
     * @return the string assembled from the input atoms
     */
    public static String join(final String connector, final Object value, final Object... values) {
        final StringBuilder buffer = new StringBuilder();
        buffer.append(value);
        for (final Object valueIt : values) {
            if (valueIt != null) {
                buffer.append(connector);  //buffer.append((buffer.length() == 0) ? "" : connector);
                buffer.append(valueIt);
            }
        }
        return buffer.toString();
    }

    /**
     * Assemble input atoms into a string, with a defined separator between each.
     *
     * @param connector the token which should delimit each input atom
     * @param values    the objects to render
     * @return the string assembled from the input atoms
     */
    public static String joinArray(final String connector, final Object[] values) {
        final StringBuilder buffer = new StringBuilder();
        for (final Object valueIt : values) {
            append(connector, buffer, valueIt);
        }
        return buffer.toString();
    }

    /**
     * Assemble input atoms into a string, with a defined separator between each.
     *
     * @param connector the token which should delimit each input atom
     * @param values    the objects to render
     * @return the string assembled from the input atoms
     */
    public static String joinCollection(final String connector, final Collection<? extends Object> values) {
        final StringBuilder buffer = new StringBuilder();
        for (final Object valueIt : values) {
            append(connector, buffer, valueIt);
        }
        return buffer.toString();
    }

    /**
     * Helper function to assemble a conglomerate string from a set of inputs.
     *
     * @param connector the token which should delimit each input atom
     * @param buffer    the buffer into which the output should be written
     * @param valueIt   the object to render
     */
    private static void append(final String connector, final StringBuilder buffer, final Object valueIt) {
        if (valueIt != null) {
            buffer.append((buffer.length() == 0) ? "" : connector);
            buffer.append(valueIt);
        }
    }

    /**
     * Delimiter that can be used when joining strings together.
     */
    public static final String DELIMITER_SLASH = "/";
}