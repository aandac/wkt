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
            result = "POINT (" + format.format(geo.getX()) + " " + format.format(geo.getY()) + ")";
        }

        return result;
    }
}
