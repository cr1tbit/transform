package com.psuwala.image.point;

import org.ejml.simple.SimpleMatrix;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.4
 */
public class Point implements Transformable<Point>{
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

    public Point(SimpleMatrix simpleMatrix) {
        this.coords = simpleMatrix;
    }

    public Point negative(){
        return new Point(this.getCoords().negative());
    }

    public double distance(Point point) {
        return pow(pow(point.getX() - this.getX(), 2) + pow(point.getY() - this.getY(), 2), 1 / 2);
    }

    public Point translate(double x, double y) {
        return translate(new Point(x, y));
    }

    public Point translate(Transformable p) {
        return translate(p.getCoords());
    }

    public Point translate(SimpleMatrix translation) {
        return new Point(getCoords().plus(translation));
    }

    public Point rotate(double angle) {
        SimpleMatrix rotationMatrix = new SimpleMatrix(2, 2, true,
                new double[]{cos(angle), -sin(angle), sin(angle), cos(angle)}
        );
        return new Point(rotationMatrix.mult(getCoords()));
    }

    public Point rotateBy(double angle, Transformable t){
        return translate(t.getCoords().negative()).rotate(angle).translate(t);
    }
}
