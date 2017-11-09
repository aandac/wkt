package com.sinergise.io.reader;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author aandac  07/11/2017.
 */
public class MultiPointGeometryReader implements GeometryReader {

    @Override
    public Geometry readWkt(String wktStr) {
        StringTokenizer tokenizer = new StringTokenizer(wktStr);

        if (wktStr.equals("MULTIPOINT EMPTY")) {
            return new MultiPoint();
        }

        //skip MULTIPOINT TEXT
        tokenizer.nextToken();

        PointGeometryReader reader = new PointGeometryReader();

        Point[] points = null;
        List<Point> pointList = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            Point point = reader.readWktFromTokenizer(tokenizer, wktStr);
            pointList.add(point);
        }

        if (!pointList.isEmpty()) {
            points = new Point[pointList.size()];
        }

        return new MultiPoint(pointList.isEmpty() ? null : pointList.toArray(points));

    }
}
