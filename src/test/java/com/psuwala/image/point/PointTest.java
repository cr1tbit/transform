package com.psuwala.image.point;

import org.ejml.simple.SimpleMatrix;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Unit test for simple App.
 */
public class PointTest {

    static double ksiDelta = 0.001;

    /**
     * Rigorous Test :-)
     */


    @Test
    public void testGetXD() {
        //can you survive it?
        Point p1 = new Point(1, 1);


        assertEquals(p1.getX(), 1.0, ksiDelta);
        assertEquals(p1.getY(), 1.0, ksiDelta);
    }

    @Test
    public void testDistancePositive() {
        //can you survive it?
        Point p1 = new Point(1, 1);
        Point p2 = new Point(4, 2);

        assertEquals(p1.distance(p2), 3.162, ksiDelta);
    }

    @Test
    public void testDistanceNegative() {
        //can you survive it?
        Point p1 = new Point(-1, -1);
        Point p2 = new Point(2, 0);

        assertEquals(p1.distance(p2), 3.162, ksiDelta);
    }

    @Test
    public void testTranslatePoint() {
        assertEquals(
                new Point(100, 100)
                        .translate(new Point(-100, 100))
                        .getX(), 0, ksiDelta);
    }

    @Test
    public void testTranslateMatrix() {
        Point pointTranslated = new Point(1, 1).translate(
                new SimpleMatrix(
                        2,
                        1,
                        true,
                        new double[]{1, 1}));

        assertTrue(pointTranslated.distance(new Point(2,2)) <= ksiDelta);

    }


}
