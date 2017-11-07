package com.sinergise.io.reader;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;

import java.util.StringTokenizer;

/**
 * @author aandac  07/11/2017.
 */
public class PolygonGeometryReader implements GeometryReader<Polygon> {

    private final static String GEO_TYPE = "POLYGON";

    @Override
    public Geometry readWkt(String wktStr) {
        StringTokenizer tokenizer = new StringTokenizer(wktStr);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.equals(GEO_TYPE)) {
                token = tokenizer.nextToken();
            }
            if (token.equals(EMPTY_IN_STR)) {
                return new Polygon();
            }



            LineStringGeometryReader reader = new LineStringGeometryReader();
            LineString outer = reader.readWktFromTokenizer(tokenizer, wktStr);

            return new Polygon(outer, null);

        }

        return new Polygon();
    }

}
