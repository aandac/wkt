package com.sinergise.io.writer;

import com.sinergise.geometry.MultiPoint;
import com.sinergise.geometry.Point;

/**
 * @author aandac  10/11/2017.
 */
public class MultiPointGeometryWriter extends AbstractGeometryWriter<MultiPoint> {

    @Override
    public String writeWkt(MultiPoint geo) {
        String result = "MULTIPOINT EMPTY";

        if (!geo.isEmpty()) {
            StringBuilder builder = new StringBuilder("");
            for (int i = 0; i < geo.size(); i++) {
                Point point = geo.get(i);
                builder.append("(").append(getPoints(point)).append("), ");
            }

            result = "MULTIPOINT (" + builder.substring(0, builder.length() - 2) + ")";
        }

        return result;
    }
}
