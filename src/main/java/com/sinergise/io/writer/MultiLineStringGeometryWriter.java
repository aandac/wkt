package com.sinergise.io.writer;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiLineString;

/**
 * @author aandac  10/11/2017.
 */
public class MultiLineStringGeometryWriter extends AbstractGeometryWriter<MultiLineString> {

    @Override
    public String writeWkt(MultiLineString geo) {
        String result = "MULTILINESTRING EMPTY";

        if (!geo.isEmpty()) {

            StringBuilder builder = new StringBuilder("");
            for (int i = 0; i < geo.size(); i++) {
                LineString lineString = geo.get(i);
                builder.append("(").append(getCoordinates(lineString)).append("), ");
            }


            result = "MULTILINESTRING (" + builder.substring(0, builder.length() - 2) + ")";
        }

        return result;
    }
}
