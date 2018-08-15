package com.psuwala.image.point;

import org.ejml.simple.SimpleMatrix;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.psuwala.image.point.PointTest.ksiDelta;
import static org.junit.Assert.*;


public class ComplexTest {
    double SOLID = 0.0;

    public boolean distEquals(Point p1, Point p2) {
        return (p1.distance(p2) == 0.0);
    }

    public boolean distEqualsDelta(Point p1, Point p2) {
        return (p1.distance(p2) <= ksiDelta);
    }
    /*
    public boolean ComplexEquals(Complex c1, Complex c2) {

    }
    */
    @Test
    public void testComplexConstructors() {
        //create complex with 2 points
        Complex c1 = new Complex(new Point(new Double(new Double(0.0)), SOLID));
        c1.add(new Complex(new Point(1, 1)));

        //create complex from list with 2 points
        List<Point> l1 = Arrays.asList(new Point(0,0),new Point(1,1));
        Complex c2 = new Complex(l1);


        //assertTrue(pointTranslated.distance(new Point(2, 2)) <= ksiDelta);
    }

    @Test
    public void testTranslateMatrix() {
        Point pointTranslated = new Point(1, 1).translate(
                new SimpleMatrix(
                        2,
                        1,
                        true,
                        new double[]{1, 1}));

        assertTrue(pointTranslated.distance(new Point(2, 2)) <= ksiDelta);
    }
}
