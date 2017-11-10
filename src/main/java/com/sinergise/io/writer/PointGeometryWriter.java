package com.sinergise.io.writer;

import com.sinergise.geometry.Point;

/**
 * @author aandac  10/11/2017.
 */
public class PointGeometryWriter implements GeometryWriter<Point> {

    @Override
    public String writeWkt(Point geo) {
        String result = "POINT EMPTY";

        if (!geo.isEmpty()) {
            result = String.format("POINT (%.0f %.0f)", geo.getX(), geo.getY());
        }

        return result;
    }
}
