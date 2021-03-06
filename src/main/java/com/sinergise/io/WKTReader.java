package com.sinergise.io;

import com.sinergise.io.reader.GeometryReaderFactory;

public class WKTReader {

    /**
     * Transforms the input WKT-formatted String into Geometry object
     */
    public Object read(String wktString) {
        return GeometryReaderFactory.getGeometry(wktString);
    }

}
