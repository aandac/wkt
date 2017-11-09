package com.sinergise.io.reader;

import java.util.IllegalFormatException;
import java.util.StringTokenizer;

/**
 * @author aandac  07/11/2017.
 */
public interface GeometryReader<T> {

    String EMPTY_IN_STR = "EMPTY";

    default T readWkt(String wktStr) throws IllegalFormatException {
        StringTokenizer tokenizer = new StringTokenizer(wktStr);
        return readWktFromTokenizer(tokenizer, wktStr);
    }

    default T readWktFromTokenizer(StringTokenizer tokenizer, String wktStr) throws IllegalFormatException {
        return null;
    }

}
