package com.sinergise.io;

import com.sinergise.geometry.Geometry;
import com.sinergise.geometry.GeometryCollection;
import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author aandac  07/11/2017.
 */
public class WKTWriterTest {

    @Test
    public void should_write_wkt_format() {
        String result_in_WKT = new WKTWriter().write(new LineString(new double[] { 30, 10, 10, 30, 40, 40 }));
        assertEquals("LINESTRING (30 10, 10 30, 40 40)", result_in_WKT);

        // TODO ali
        new GeometryCollection<Geometry>(new Geometry[]{new Point(4,6), new LineString(new double[] {4,6,7,10})});
    }

}
