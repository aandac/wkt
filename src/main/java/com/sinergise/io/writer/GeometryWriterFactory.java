package com.sinergise.io.writer;

import com.sinergise.geometry.*;

/**
 * @author aandac  07/11/2017.
 */
public final class GeometryWriterFactory {

    private GeometryWriterFactory() {
    }

    //    Point, LineString, Polygon, GeometryCollection, MultiPoint, MultiLineString ali MultiPolygon

    public static String writeGeometry(Geometry geometry) {
        if (geometry == null) {
            return null;
        }

        GeometryWriter writer = null;
        if (geometry instanceof Point) {
            writer = new PointGeometryWriter();
        } else if (geometry instanceof LineString) {
            writer = new LineStringGeometryWriter();
        } else if (geometry instanceof Polygon) {
            writer = new PolygonGeometryWriter();
        } else if (geometry instanceof MultiPoint) {
            writer = new MultiPointGeometryWriter();
        } else if (geometry instanceof MultiLineString) {
            writer = new MultiLineStringGeometryWriter();
        } else if (geometry instanceof MultiPolygon) {
            writer = new MultiPolygonGeometryWriter();
        } else if (geometry instanceof GeometryCollection) {
            writer = new GeometryCollectionGeometryWriter();
        } else {
            throw new IllegalArgumentException("Unsupported geometry type. " + geometry.getClass().getName());
        }

        return writer.writeWkt(geometry);
    }

}
