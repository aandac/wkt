package com.sinergise.io.reader;

import com.sinergise.geometry.Geometry;

import java.util.IllegalFormatException;
import java.util.StringTokenizer;

/**
 * @author aandac  07/11/2017.
 */
public interface GeometryReader<T extends Geometry> {

    String EMPTY_IN_STR = "EMPTY";

    /**
     * Reads the Well-Known-Text formatted geometry object string and converts the Geometry object instance.
     *
     * @param wktStr well-known-text formatted string
     * @return geometry object instance
     * @throws IllegalFormatException throws exception if unknown formatted string
     */
    default T readWkt(String wktStr) throws IllegalFormatException {
        StringTokenizer tokenizer = new StringTokenizer(wktStr);
        return readWktFromTokenizer(tokenizer, wktStr);
    }

    /**
     * Reads the well-known-text formatted string from the StringTokenizer class.
     *
     * @param tokenizer string tokenizer
     * @param wktStr    well-known-text formatted string
     * @return geometry object instance
     * @throws IllegalFormatException throws exception if unknown formatted string
     */
    default T readWktFromTokenizer(StringTokenizer tokenizer, String wktStr) throws IllegalFormatException {
        return null;
    }

}
