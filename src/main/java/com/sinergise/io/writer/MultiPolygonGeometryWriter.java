package com.sinergise.io.writer;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.MultiPolygon;
import com.sinergise.geometry.Polygon;

/**
 * @author aandac  10/11/2017.
 */
public class MultiPolygonGeometryWriter extends BasePolygonGeometryWriter<MultiPolygon> {

    //MULTIPOLYGON (((30 20, 45 40, 10 40, 30 20)), ((15 5, 40 10, 10 20, 5 10, 15 5)))
    @Override
    public String writeWkt(MultiPolygon geo) {
        int polySize = geo.size();
        String result = "MULTIPOLYGON EMPTY";
        if (polySize == 0) {
            return result;
        }

        result = "MULTIPOLYGON (";
        for (int i = 0; i < polySize; i++) {
            Polygon polygon = geo.get(i);
            LineString outer = polygon.getOuter();

            if (!outer.isEmpty()) {
                String points = getCoordinates(outer);
                result = result + "((" + points + ")";
            }

            result = getHoles(polygon, result);

            result = result + "), ";
        }

        result = result.substring(0, result.length() - 2) + ")";

        return result;
    }

}
