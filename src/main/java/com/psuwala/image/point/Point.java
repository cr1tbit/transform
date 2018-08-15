package com.psuwala.image.point;

import org.ejml.simple.SimpleMatrix;

import static java.lang.Math.pow;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.4
 */
public class Point {
    final private SimpleMatrix coords;

    public SimpleMatrix getCoords() {
        return coords;
    }

    public double getX() {
        return coords.get(0);
    }

    public double getY() {
        return coords.get(1);
    }

    public Point(double x, double y) {
        this(new SimpleMatrix(1, 2, true, new double[]{x, y}));
    }

    public Point(SimpleMatrix simpleMatrix){
        this.coords = simpleMatrix;
    }

    public double distance(Point point) {
        return pow(pow(point.getX() - this.getX(), 2) + pow(point.getY() - this.getY(), 2), 1 / 2);
    }

    public Point translate(double x, double y) {
        return translate(new Point(x,y));
    }

    public Point translate(Point p) {
        return translate(p.getCoords());
    }

    public Point translate(SimpleMatrix translation) {
        return new Point(getCoords().plus(translation));
    }
}
