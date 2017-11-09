package com.sinergise.io.reader;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author aandac  07/11/2017.
 */
public class MultiLineStringGeometryReader implements GeometryReader {

    @Override
    public Geometry readWkt(String wktStr) {
        StringTokenizer tokenizer = new StringTokenizer(wktStr);

        if (wktStr.equals("MULTILINESTRING EMPTY")) {
            return new MultiLineString();
        }

        //skip MULTILINESTRING TEXT
        tokenizer.nextToken();

        LineStringGeometryReader reader = new LineStringGeometryReader();

        LineString[] lines = null;
        List<LineString> lineList = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            LineString hole = reader.readWktFromTokenizer(tokenizer, wktStr);
            lineList.add(hole);
        }

        if (!lineList.isEmpty()) {
            lines = new LineString[lineList.size()];
        }

        return new MultiLineString(lineList.isEmpty() ? null : lineList.toArray(lines));

    }
}
