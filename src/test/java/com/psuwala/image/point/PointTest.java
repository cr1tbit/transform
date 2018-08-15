package com.psuwala.image.point;

import org.ejml.simple.SimpleMatrix;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static java.lang.Math.*;

/**
 * Unit test for simple App.
 */
public class PointTest {

    static double ksiDelta = 0.005;

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
    public void testEqualsHash() {
        Point p1 = new Point (4.20,2.137);
        Point p2 = new Point (4.20,2.137);

        assertTrue(p1.equals(p2));

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

    @Test
    public void testRotateSimple() {
        assertEquals(new Point(1, 1)
                .rotate(PI*1/2)
                .distance(new Point(-1,1)),0.0,ksiDelta);
    }

    @Test
    public void testRotateAdvanced() {
        assertEquals(new Point(1, 1)
                .rotate(PI*3/4)
                .distance(new Point(-1.41,0)),0.0,ksiDelta);
    }

    @Test
    public void testRotateBySimple() {
        Point p1 = new Point(1,1).rotateBy(PI,new Point (1,0));

        assertEquals(p1.distance(new Point(1,-1)),0.0,ksiDelta);
    }
    // TODO add advanced rotation
    @Test
    public void testRotateByAdvanced() {
        Point p1 = new Point(1,1).rotateBy(PI,new Point (1,0));

        assertEquals(p1.distance(new Point(1,-1)),0.0,ksiDelta);
    }



}
