package com.sinergise.io;

import com.sinergise.geometry.*;
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

        String wkt_poly_geo3 = "POLYGON ((35 10, 45 45, 15 40, 10 20, 35 10), (20 30, 35 35, 30 20, 20 30), (20 30, 35 35, 30 20, 20 30))";
        LineString outer3 = new LineString(new double[] { 35, 10, 45, 45, 15, 40, 10, 20, 35, 10 });
        LineString[] holes3 = { new LineString(new double[] { 20, 30, 35, 35, 30, 20, 20, 30 }),
                new LineString(new double[] { 20, 30, 35, 35, 30, 20, 20, 30 }) };
        assertEquals(new Polygon(outer3, holes3), reader.read(wkt_poly_geo3));

        String wkt_multipoint_empty_geo = "MULTIPOINT EMPTY";
        assertEquals(new MultiPoint(), reader.read(wkt_multipoint_empty_geo));

        String wkt_multipoint_geo = "MULTIPOINT ((10 40), (40 30), (20 20), (30 10))";
        Point[] points = new Point[] { new Point(10, 40), new Point(40, 30), new Point(20, 20), new Point(30, 10) };
        assertEquals(new MultiPoint(points), reader.read(wkt_multipoint_geo));

        String wkt_multiline_empty_geo = "MULTILINESTRING EMPTY";
        assertEquals(new MultiLineString(), reader.read(wkt_multiline_empty_geo));

        String wkt_multiline_geo = "MULTILINESTRING ((10 10, 20 20, 10 40), (40 40, 30 30, 40 20, 30 10))";
        MultiLineString expectedMulti = new MultiLineString(new LineString[] { new LineString(new double[] { 10, 10, 20, 20, 10, 40 }),
                new LineString(new double[] { 40, 40, 30, 30, 40, 20, 30, 10 }) });
        assertEquals(expectedMulti, reader.read(wkt_multiline_geo));

        String wkt_multipolygon_empty_geo = "MULTIPOLYGON EMPTY";
        assertEquals(new MultiPolygon(), reader.read(wkt_multipolygon_empty_geo));

        String wkt_multipoly_geo1 = "MULTIPOLYGON (((30 20, 45 40, 10 40, 30 20)), ((15 5, 40 10, 10 20, 5 10, 15 5)))";
        Polygon[] polygons = new Polygon[] { new Polygon(new LineString(new double[] { 30, 20, 45, 40, 10, 40, 30, 20 }), null),
                new Polygon(new LineString(new double[] { 15, 5, 40, 10, 10, 20, 5, 10, 15, 5 }), null) };
        assertEquals(new MultiPolygon(polygons), reader.read(wkt_multipoly_geo1));

//        String wkt_multipoly_geo2 = "MULTIPOLYGON (((40 40, 20 45, 45 30, 40 40)), ((20 35, 10 30, 10 10, 30 5, 45 20, 20 35), (30 20, 20 15, 20 25, 30 20)))";
//        Polygon[] polygons2 = new Polygon[] { new Polygon(new LineString(new double[] { 40, 40, 20, 45, 45, 30, 40, 40 }), null),
//                new Polygon(new LineString(new double[] { 20, 35, 10, 30, 10, 10, 30, 5, 45, 20, 20, 35 }),
//                        new LineString[] { new LineString(new double[] { 30, 20, 20, 15, 20, 25, 30, 20 }) }) };
//        assertEquals(new MultiPolygon(polygons2), reader.read(wkt_multipoly_geo2));

    }

}
