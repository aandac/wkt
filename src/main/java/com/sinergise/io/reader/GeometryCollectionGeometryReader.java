package com.sinergise.io.reader;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author aandac  07/11/2017.
 */
public class GeometryCollectionGeometryReader implements GeometryReader {

    @Override
    public Geometry readWkt(String wktStr) {
        if (wktStr.equals("GEOMETRYCOLLECTION EMPTY")) {
            return new GeometryCollection<>();
        }

        StringTokenizer tokenizer = new StringTokenizer(wktStr);
        // skip GEOMETRYCOLLECTION
        tokenizer.nextToken();
        // skip first "("
        tokenizer.nextToken();

        String geoStr = "";
        List<String> geoStrList = new ArrayList<>();
        while (tokenizer.hasMoreTokens()) {
            String t = tokenizer.nextToken();
            // create a sub well know text for each geometry type
            if (t.matches("^[A-Z].*$") && !geoStr.isEmpty()) {
                geoStrList.add(geoStr.substring(0,geoStr.length()-1));
                geoStr = t;
            } else {
                geoStr = geoStr + " " + t;
            }
        }

        // add last geometry type to the list if exist
        if (!geoStr.isEmpty()) {
            geoStrList.add(geoStr.substring(0,geoStr.length()-1));
        }

        List<Geometry> geometries = new ArrayList<>();
        // create geometry objects from each string
        for (String geoString : geoStrList) {
            geometries.add(GeometryReaderFactory.getGeometry(geoString.trim()));
        }
        return new GeometryCollection<>(geometries);
    }
}
