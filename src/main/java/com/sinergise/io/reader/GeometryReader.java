package com.sinergise.io.reader;

import com.sinergise.geometry.Geometry;

import java.util.IllegalFormatException;
import java.util.StringTokenizer;

/**
 * @author aandac  07/11/2017.
 */
public interface GeometryReader<T> {

    String EMPTY_IN_STR = "EMPTY";

    Geometry readWkt(String wktStr) throws IllegalFormatException;

    default T readWktFromTokenizer(StringTokenizer tokenizer, String wktStr) throws IllegalFormatException {
        return null;
    }

}
