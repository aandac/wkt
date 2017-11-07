package com.sinergise.io.reader;

import com.sinergise.geometry.Geometry;
import com.sinergise.io.GeometryType;

/**
 * @author aandac  07/11/2017.
 */
public final class GeometryReaderFactory {

    private GeometryReaderFactory() {
    }

    //    Point, LineString, Polygon, GeometryCollection, MultiPoint, MultiLineString ali MultiPolygon

    public static Geometry getGeometry(String wktString) {
        if (wktString == null) {
            return null;
        }

        GeometryReader reader;
        if (wktString.startsWith(GeometryType.POINT.toString())) {
            reader = new PointGeometryReader();
        } else if (wktString.startsWith(GeometryType.LINESTRING.toString())) {
            reader = new LineStringGeometryReader();
        } else if (wktString.startsWith(GeometryType.POLYGON.toString())) {
            reader = new PolygonGeometryReader();
        } else if (wktString.startsWith("GEOMETRYCOLLECTION")) {
            reader = new GeometryCollectionGeometryReader();
        } else if (wktString.startsWith("MULTIPOINT")) {
            reader = new MultiPointGeometryReader();
        } else if (wktString.startsWith("MULTILINESTRING")) {
            reader = new MultiLineStringGeometryReader();
        } else if (wktString.startsWith("MULTIPOLYGON")) {
            reader = new MultiPolygonGeometryReader();
        } else {
            throw new IllegalArgumentException("Unsupported WKT string.Str=" + wktString);
        }

        return reader.readWkt(wktString);
    }

}
