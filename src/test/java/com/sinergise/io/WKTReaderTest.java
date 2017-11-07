package com.sinergise.io;

import com.sinergise.geometry.LineString;
import com.sinergise.geometry.Point;
import com.sinergise.geometry.Polygon;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author aandac  07/11/2017.
 */
public class WKTReaderTest {

    @Test
    public void should_read_wkt_format() {
        WKTReader reader = new WKTReader();
        String wkt_point_geo = "POINT (30 10)";
        assertEquals(new Point(30, 10), reader.read(wkt_point_geo));

        String wkt_point_empty_geo = "POINT EMPTY";
        assertEquals(new Point(), reader.read(wkt_point_empty_geo));

        String wkt_line_empty_geo = "LINESTRING EMPTY";
        assertEquals(new LineString(), reader.read(wkt_line_empty_geo));

        String wkt_line_geo = "LINESTRING (30 10, 10 30, 40 40)";
        LineString expected = new LineString(new double[] { 30, 10, 10, 30, 40, 40 });
        assertEquals(expected, reader.read(wkt_line_geo));

        String wkt_poly_empty_geo = "POLYGON EMPTY";
        assertEquals(new Polygon(), reader.read(wkt_poly_empty_geo));

        String wkt_poly_geo1 = "POLYGON ((30 10, 40 40, 20 40, 10 20, 30 10))";
        assertEquals(new Polygon(new LineString(new double[] { 30, 10, 40, 40, 20, 40, 10, 20, 30, 10 }), null),
                reader.read(wkt_poly_geo1));

        String wkt_poly_geo2 = "POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10), (20 30, 35 35, 30 20, 20 30))";
        LineString outer = new LineString(new double[] { 35, 10, 45, 45, 15, 40, 10, 20, 35, 10 });
        LineString[] holes = { new LineString(new double[] { 20, 30, 35, 35, 30, 20, 20, 30 }) };
        assertEquals(new Polygon(outer, holes), reader.read(wkt_poly_geo2));
    }

}
