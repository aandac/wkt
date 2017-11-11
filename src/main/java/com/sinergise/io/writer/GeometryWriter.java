package com.sinergise.io.writer;

import com.sinergise.geometry.Geometry;

import java.text.DecimalFormat;

/**
 * @author aandac  07/11/2017.
 */
public interface GeometryWriter<T extends Geometry> {

    DecimalFormat format = new DecimalFormat("0.#");

    String writeWkt(T geo);

}
