package com.sinergise.io.reader;

import com.sinergise.geometry.LineString;
import com.sinergise.io.GeometryType;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author aandac  07/11/2017.
 */
public class LineStringGeometryReader implements GeometryReader<LineString> {

    @Override
    public LineString readWktFromTokenizer(StringTokenizer tokenizer, String wktStr) throws IllegalFormatException {
        List<Double> coordinates = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.equals(GeometryType.LINESTRING.toString())) {
                token = tokenizer.nextToken();
            }
            if (token.equals(EMPTY_IN_STR)) {
                return new LineString();
            }

            if (token.startsWith("(((")) {
                coordinates.add(Double.valueOf(token.substring(3)));
            } else if (token.startsWith("((")) {
                coordinates.add(Double.valueOf(token.substring(2)));
            } else if (token.startsWith("(")) {
                coordinates.add(Double.valueOf(token.substring(1)));
            } else if (token.endsWith(")))")) {
                coordinates.add(Double.valueOf(token.substring(0, token.length() - 3)));
            } else if (token.endsWith("))")) {
                coordinates.add(Double.valueOf(token.substring(0, token.length() - 2)));
            } else if (token.endsWith(")")) {
                coordinates.add(Double.valueOf(token.substring(0, token.length() - 1)));
            } else if (isDigit(token)) {
                coordinates.add(Double.valueOf(token));
            } else if (token.endsWith(")),")) {
                coordinates.add(Double.valueOf(token.substring(0, token.length() - 3)));
                Double[] points = new Double[coordinates.size()];
                return new LineString(ArrayUtils.toPrimitive(coordinates.toArray(points)));
            } else if (token.endsWith("),")) {
                coordinates.add(Double.valueOf(token.substring(0, token.length() - 2)));
                Double[] points = new Double[coordinates.size()];
                return new LineString(ArrayUtils.toPrimitive(coordinates.toArray(points)));
            } else if (token.endsWith(",")) {
                coordinates.add(Double.valueOf(token.substring(0, token.length() - 1)));
            } else {
                throw new IllegalArgumentException("Invalid well-known-text. Text:" + wktStr);
            }
        }
        Double[] points = new Double[coordinates.size()];
        return new LineString(ArrayUtils.toPrimitive(coordinates.toArray(points)));
    }

    private boolean isDigit(String token) {
        boolean result = true;
        try {
            double v = Double.parseDouble(token);
        } catch (NumberFormatException e) {
            result = false;
        }
        return result;
    }
}
