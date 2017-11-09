package com.sinergise.io.reader;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Polygon;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author aandac  07/11/2017.
 */
public class MultiPolygonGeometryReader implements GeometryReader<MultiPolygon> {

    @Override
    public MultiPolygon readWkt(String wktStr) {
        StringTokenizer tokenizer = new StringTokenizer(wktStr);

        if (wktStr.equals("MULTIPOLYGON EMPTY")) {
            return new MultiPolygon();
        }

        PolygonGeometryReader reader = new PolygonGeometryReader();

        Polygon[] polygons = null;
        List<Polygon> polygonList = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {

            LineStringGeometryReader lineReader = new LineStringGeometryReader();
            LineString outer = lineReader.readWktFromTokenizer(tokenizer, wktStr);


            while (tokenizer.hasMoreTokens()) {

            }

            polygonList.add(pol);
        }

        if (!polygonList.isEmpty()) {
            polygons = new Polygon[polygonList.size()];
        }

        return new MultiPolygon(polygonList.isEmpty() ? null : polygonList.toArray(polygons));
    }

}
