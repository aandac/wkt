package com.sinergise.io.writer;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Polygon;

/**
 * @author aandac  10/11/2017.
 */
public class PolygonGeometryWriter extends BasePolygonGeometryWriter<Polygon> {

    @Override
    public String writeWkt(Polygon geo) {
        LineString outer = geo.getOuter();
        String result = "POLYGON EMPTY";
        if (outer == null) {
            return result;
        }

        if (!outer.isEmpty()) {
            String points = getCoordinates(outer);
            result = "POLYGON ((" + points + ")";
        }

        result = getHoles(geo, result);

        result = result + ")";

        return result;
    }


}
