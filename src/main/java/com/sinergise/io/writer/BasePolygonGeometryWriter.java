package com.sinergise.io.writer;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;

abstract class BasePolygonGeometryWriter<T extends Geometry> extends AbstractGeometryWriter<T> {

    /**
     * Returns the Well-Known-Text format of the polygon object.
     *
     * @param polygon polygon instance
     * @param baseWkt base well-known-text to be written
     * @return concatenated wkt string with new polygon holes
     */
    String getHoles(Polygon polygon, String baseWkt) {
        StringBuilder builder = new StringBuilder(baseWkt);
        if (polygon.getNumHoles() > 0) {
            for (int i = 0; i < polygon.getNumHoles(); i++) {
                LineString lineString = polygon.getHole(i);
                String points = getCoordinates(lineString);
                builder.append(", (").append(points).append(")");
            }
        }
        return builder.toString();
    }
}
