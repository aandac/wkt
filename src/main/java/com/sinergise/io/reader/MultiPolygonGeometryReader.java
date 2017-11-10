package com.sinergise.io.reader;

import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Polygon;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author aandac  07/11/2017.
 */
public class MultiPolygonGeometryReader implements GeometryReader<MultiPolygon> {

    @Override
    public MultiPolygon readWkt(String wktStr) {

        if (wktStr.equals("MULTIPOLYGON EMPTY")) {
            return new MultiPolygon();
        }

        // Devide each polygon string into 1 polygon
        wktStr = wktStr.replace("MULTIPOLYGON", "");
        String[] polygonStr = wktStr.split("\\)\\),");

        PolygonGeometryReader reader = new PolygonGeometryReader();
        Polygon[] polygons = null;
        List<Polygon> polygonList = new ArrayList<>();
        for (int i = 0; i < polygonStr.length; i++) {
            String poly = polygonStr[i];
            if (!poly.endsWith(")))")) {
                poly = poly + "))";
            }
            poly = "POLYGON " + poly;

            StringTokenizer tokenizer = new StringTokenizer(poly);
            while (tokenizer.hasMoreTokens()) {

                Polygon polygon = reader.readWktFromTokenizer(tokenizer, wktStr);
                polygonList.add(polygon);
            }
        }
        if (!polygonList.isEmpty()) {
            polygons = new Polygon[polygonList.size()];
        }
        return new MultiPolygon(polygonList.isEmpty() ? null : polygonList.toArray(polygons));
    }

}
