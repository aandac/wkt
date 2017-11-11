package com.sinergise.io.writer;

import com.sinergise.geometry.LineString;

/**
 * @author aandac  10/11/2017.
 */
public class LineStringGeometryWriter implements GeometryWriter<LineString> {

    @Override
    public String writeWkt(LineString geo) {
        String result = "LINESTRING EMPTY";

        if (!geo.isEmpty()) {
            StringBuilder points = new StringBuilder();
            for (int i = 0; i < geo.getNumCoords(); i++) {
                points.append(format.format(geo.getX(i))).append(" ").append(format.format(geo.getY(i))).append(", ");
            }

            result = "LINESTRING (" + points.toString().substring(0, points.length() - 2) + ")";
        }

        return result;
    }
}
