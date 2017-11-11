package com.sinergise.io.writer;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;

/**
 * @author aandac  10/11/2017.
 */
public class GeometryCollectionGeometryWriter implements GeometryWriter<GeometryCollection> {

    @Override
    public String writeWkt(GeometryCollection geo) {
        String result = "GEOMETRYCOLLECTION EMPTY";
        if (!geo.isEmpty()) {
            result = "GEOMETRYCOLLECTION ( ";
            for (int i = 0; i < geo.size(); i++) {
                Geometry geometry = geo.get(i);
                String wktGeo = GeometryWriterFactory.writeGeometry(geometry);
                result = result + wktGeo + ", ";

            }
            result = result.substring(0, result.length() - 2) + ")";

        }
        return result;
    }
}
