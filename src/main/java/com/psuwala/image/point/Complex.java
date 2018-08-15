package com.psuwala.image.point;

import org.ejml.simple.SimpleBase;
import org.ejml.simple.SimpleMatrix;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.4
 */
public class Complex implements Transformable<Complex> {
    private final List<Point> points;

    public Complex(Point point) {
        points = Collections.singletonList(point);
    }

    public Complex(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public Complex add(Complex complex) {
        return new Complex(Stream.concat(points.stream(), complex.points.stream())
                .collect(Collectors.toList()));
    }

    @Override
    public Complex translate(Transformable t) {
        return new Complex(points.stream().map(x -> x.translate(t)).collect(Collectors.toList()));
    }

    @Override
    public Complex rotate(double angle) {
        return new Complex(points.stream().map(x -> x.rotate(angle)).collect(Collectors.toList()));
    }

    @Override
    public Complex rotateBy(double angle, Transformable t) {
        return new Complex(points.stream().map(x -> x.rotateBy(angle, t)).collect(Collectors.toList()));
    }

    //coords returns center of mass
    @Override
    public SimpleMatrix getCoords() {
        SimpleMatrix zero = new SimpleMatrix(2, 1, true, new double[]{0, 0});
        return points.stream()
                .map(Point::getCoords)
                .reduce(zero, SimpleBase::plus)
                .divide(points.size());
    }
}
