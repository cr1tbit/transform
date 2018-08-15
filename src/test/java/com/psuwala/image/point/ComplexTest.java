package com.psuwala.image.point;

import org.ejml.simple.SimpleMatrix;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.psuwala.image.point.PointTest.ksiDelta;
import static org.junit.Assert.*;

import static java.lang.Math.*;


public class ComplexTest {

    Complex cSquare = new Complex(new Point(0, 0))
            .add(new Complex(new Point(1, 0)))
            .add(new Complex(new Point(1, 1)))
            .add(new Complex(new Point(0, 1)));

    Complex cLine = new Complex(new Point(-1, 0))
            .add(new Complex(new Point(1, 0)));

    Point pAbove = new Point(1, 0);

    @Test
    public void testComplexEqualsHash() {
        //one element complex:
        Complex c1 = new Complex(new Point(0.0, 0.0))
                .add(new Complex((new Point(0.0, 0.0))));
        Complex c2 = new Complex(new Point(0.0, 0.0))
                .add(new Complex((new Point(0.0, 0.0))));
        Complex cBad = new Complex(new Point(1.0, 0.0))
                .add(new Complex((new Point(0.0, 0.0))));

        assertEquals(c1.getPoints(), c2.getPoints());
        assertEquals(c1, c2);

        assertNotEquals(c1.getPoints(), cBad.getPoints());
        assertNotEquals(c1, cBad);

        assertEquals(c1.getPoints().hashCode(), c2.getPoints().hashCode());
        assertEquals(c1.hashCode(), c2.hashCode());

        assertNotEquals(c1.getPoints().hashCode(), cBad.getPoints().hashCode());
        assertNotEquals(c1.hashCode(), cBad.hashCode());
    }


    @Test
    public void testComplexConstructors() {
        //create complex with 2 points
        Complex c1 = new Complex(new Point(0.0, 0.0))
                .add(new Complex(new Point(1, 1)));

        //create complex from list with 2 points
        List<Point> l1 = Arrays.asList(new Point(0.0, 0.0), new Point(1, 1));
        Complex c2 = new Complex(l1);

        assertEquals(c1.getPoints(), c2.getPoints());
        assertEquals(c1, c2);
    }


    @Test
    public void testRotate() {
        assertEquals(cLine.rotate(PI / 4).getPoints().get(0).getX(), -0.707, ksiDelta);
        assertEquals(cLine.rotate(PI / 4).getPoints().get(0).getY(), -0.707, ksiDelta);
    }

    @Test
    public void testRotateBy() {
        assertEquals(cLine.rotateBy(-PI / 4, pAbove).getPoints().get(0).getX(), 1 - (1.41), ksiDelta);
        assertEquals(cLine.rotateBy(-PI / 4, pAbove).getPoints().get(0).getY(), (1.41), ksiDelta);

    }

    @Test
    public void testGetCoords() {
        assertEquals(new Point(cSquare.getCoords()), new Point(0.5, 0.5));
    }

    @Test
    public void testDistance() {
        assertEquals(cSquare.distance(new Complex(new Point(0.5, 0.5))),
                0.707,
                ksiDelta);
    }
}
