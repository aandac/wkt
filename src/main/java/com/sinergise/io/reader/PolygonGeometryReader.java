package com.sinergise.io.reader;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;
import com.sinergise.io.GeometryType;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author aandac  07/11/2017.
 */
public class PolygonGeometryReader implements GeometryReader<Polygon> {

    @Override
    public Polygon readWktFromTokenizer(StringTokenizer tokenizer, String wktStr) throws IllegalFormatException {
        if (wktStr.equals("POLYGON EMPTY")) {
            return new Polygon();
        }
        String token = tokenizer.nextToken();

        LineString outer = null;
        List<LineString> holeList = new ArrayList<>();
        LineString[] holes = null;
        if (token.contains(GeometryType.POLYGON.toString())) {

            LineStringGeometryReader reader = new LineStringGeometryReader();
            outer = reader.readWktFromTokenizer(tokenizer, wktStr);

            while (tokenizer.hasMoreTokens()) {
                LineString hole = reader.readWktFromTokenizer(tokenizer, wktStr);
                holeList.add(hole);
            }

            if (!holeList.isEmpty()) {
                holes = new LineString[holeList.size()];
            }
        }

        return new Polygon(outer, holeList.isEmpty() ? null : holeList.toArray(holes));

    }
}
