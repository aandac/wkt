package com.sinergise.io.writer;


import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Point;

import java.text.DecimalFormat;

abstract class AbstractGeometryWriter<T extends Geometry> implements GeometryWriter<T> {

    private DecimalFormat format = new DecimalFormat("0.#");

    /**
     * Gets the Well-Known-Text format of the linestring.
     *
     * @param lineString linestring object instance
     * @return well-known-text formatted string
     */
    String getCoordinates(LineString lineString) {
        StringBuilder points = new StringBuilder();
        for (int i = 0; i < lineString.getNumCoords(); i++) {
            points.append(format.format(lineString.getX(i))).append(" ").append(format.format(lineString.getY(i))).append(", ");
        }
        return points.toString().substring(0, points.length() - 2);
    }

    String getPoints(Point point) {
        return format.format(point.getX()) + " " + format.format(point.getY());
    }

}
