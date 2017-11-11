package com.sinergise.io.writer;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;

/**
 * @author aandac  10/11/2017.
 */
public class PolygonGeometryWriter implements GeometryWriter<Polygon> {

    @Override
    public String writeWkt(Polygon geo) {
        LineString outer = geo.getOuter();
        String result = "POLYGON EMPTY";
        if (outer == null) {
            return result;
        }

        if (!outer.isEmpty()) {
            String points = getCoordinates(outer);
            result = "POLYGON ((" + points + ")";
        }

        if (geo.getNumHoles() > 0) {
            for (int i = 0; i < geo.getNumHoles(); i++) {
                LineString lineString = geo.getHole(i);
                String points = getCoordinates(lineString);
                result = result+ ", (" + points + ")";
            }
        }

        result = result + ")";

        return result;
    }

    private String getCoordinates(LineString outer) {
        StringBuilder points = new StringBuilder();
        for (int i = 0; i < outer.getNumCoords(); i++) {
            points.append(format.format(outer.getX(i))).append(" ").append(format.format(outer.getY(i))).append(", ");
        }
        return points.toString().substring(0, points.length() - 2);
    }
}
