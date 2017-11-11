package com.sinergise.io.writer;

import com.sinergise.geometry.Geometry;

/**
 * @author aandac  07/11/2017.
 */
public interface GeometryWriter<T extends Geometry> {

    /**
     * Converts the Geometry object to the Well-Known-Text formatted string.
     *
     * @param geo geometry object instance
     * @return well-known-text formatted geometry object string
     */
    String writeWkt(T geo);

}
