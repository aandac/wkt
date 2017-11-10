package com.sinergise.io.writer;

import com.sinergise.geometry.Geometry;

/**
 * @author aandac  07/11/2017.
 */
public interface GeometryWriter<T extends Geometry> {

    String writeWkt(T geo);

}
