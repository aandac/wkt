package com.sinergise.io.writer;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;

/**
 * @author aandac  10/11/2017.
 */
public class GeometryCollectionGeometryWriter extends AbstractGeometryWriter<GeometryCollection> {

    @Override
    public String writeWkt(GeometryCollection geo) {
        String result = "GEOMETRYCOLLECTION EMPTY";
        if (!geo.isEmpty()) {
            StringBuilder builder = new StringBuilder("GEOMETRYCOLLECTION ( ");
            for (int i = 0; i < geo.size(); i++) {
                Geometry geometry = geo.get(i);
                String wktGeo = GeometryWriterFactory.writeGeometry(geometry);
                builder.append(wktGeo).append(", ");

            }
            result = builder.substring(0, builder.length() - 2) + ")";

        }
        return result;
    }
}
