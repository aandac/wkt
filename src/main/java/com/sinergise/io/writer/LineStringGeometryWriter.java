package com.sinergise.io.writer;

import com.sinergise.geometry.LineString;

/**
 * @author aandac  10/11/2017.
 */
public class LineStringGeometryWriter extends AbstractGeometryWriter<LineString> {

    @Override
    public String writeWkt(LineString geo) {
        String result = "LINESTRING EMPTY";

        if (!geo.isEmpty()) {
            String coordinates = getCoordinates(geo);
            result = "LINESTRING (" + coordinates + ")";
        }

        return result;
    }
}
