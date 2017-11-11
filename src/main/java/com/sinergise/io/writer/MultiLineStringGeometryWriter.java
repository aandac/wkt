package com.sinergise.io.writer;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;
import com.sinergise.geometry.Point;

/**
 * @author aandac  10/11/2017.
 */
public class MultiLineStringGeometryWriter implements GeometryWriter<MultiLineString> {

    @Override
    public String writeWkt(MultiLineString geo) {
        String result = "MULTILINESTRING EMPTY";

        if (!geo.isEmpty()) {

            String points = "";
            for (int i = 0; i < geo.size(); i++) {
                LineString lineString = geo.get(i);
                StringBuilder lines = new StringBuilder();
                for (int j = 0; j < lineString.getNumCoords(); j++) {
                    lines.append(format.format(lineString.getX(j))).append(" ").append(format.format(lineString.getY(j))).append(", ");
                }
                points = points + "(" + lines.substring(0, lines.length() - 2) + "), ";
            }


            result = "MULTILINESTRING (" + points.substring(0, points.length() - 2) + ")";
        }

        return result;
    }
}
