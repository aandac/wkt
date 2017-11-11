package com.sinergise.io.writer;

import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.Point;

import java.util.Spliterator;

/**
 * @author aandac  10/11/2017.
 */
public class MultiPointGeometryWriter implements GeometryWriter<MultiPoint> {

    @Override
    public String writeWkt(MultiPoint geo) {
        String result = "MULTIPOINT EMPTY";

        if (!geo.isEmpty()) {
            String points = "";
            for (int i = 0; i < geo.size(); i++) {
                Point point = geo.get(i);
                points = points + "(" + format.format(point.getX()) + " " + format.format(point.getY()) + "), ";
            }

            result = "MULTIPOINT (" + points.substring(0, points.length() - 2) + ")";
        }

        return result;
    }
}
