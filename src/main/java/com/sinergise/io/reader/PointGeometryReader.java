package com.sinergise.io.reader;

import com.sinergise.geometry.Point;

import java.util.IllegalFormatException;
import java.util.StringTokenizer;

/**
 * @author aandac  07/11/2017.
 */
public class PointGeometryReader implements GeometryReader<Point> {

    private final static String GEO_TYPE = "POINT";

    @Override
    public Point readWktFromTokenizer(StringTokenizer tokenizer, String wktStr) throws IllegalFormatException {
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

            if (token.startsWith("((")) {
                point1 = Double.valueOf(token.substring(2));
            } else if (token.startsWith("(")) {
                point1 = Double.valueOf(token.substring(1));
            } else if (token.endsWith("))")) {
                point2 = Double.valueOf(token.substring(0, token.length() - 2));
                return new Point(point1, point2);
            } else if (token.endsWith(")")) {
                point2 = Double.valueOf(token.substring(0, token.length() - 1));
            } else if (token.endsWith("),")) {
                point2 = Double.valueOf(token.substring(0, token.length() - 2));
                return new Point(point1, point2);
            } else {
                throw new IllegalArgumentException("Invalid well-known-text. Text:" + wktStr);
            }
        }
        return new Point(point1, point2);

    }
}
