package com.sinergise.io.reader;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.Point;

import java.util.StringTokenizer;

/**
 * @author aandac  07/11/2017.
 */
public class PointGeometryReader implements GeometryReader {

    private final static String GEO_TYPE = "POINT";

    @Override
    public Geometry readWkt(String wktStr) throws IllegalArgumentException {
        StringTokenizer tokenizer = new StringTokenizer(wktStr);
        double point1 = 0;
        double point2 = 0;
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.equals(GEO_TYPE)) {
                token = tokenizer.nextToken();
            }
            if (token.equals(EMPTY_IN_STR)) {
                return new Point();
            }

            if (token.startsWith("(")) {
                point1 = Double.valueOf(token.substring(1));
            } else if (token.endsWith(")")) {
                point2 = Double.valueOf(token.substring(0, token.length() - 1));
            } else {
                throw new IllegalArgumentException("Invalid well-known-text. Text:" + wktStr);
            }
        }
        return new Point(point1, point2);
    }
}
