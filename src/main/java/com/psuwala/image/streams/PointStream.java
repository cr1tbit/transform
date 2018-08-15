package com.psuwala.image.streams;

import com.psuwala.image.point.Complex;
import com.psuwala.image.point.Point;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.4
 */
public class PointStream {
    List<Point> points;

    public PointStream(List<Point> points) {
        this.points = points;
    }

    public List<Point> getPoints() {
        return points;
    }

    public ComplexStream group(double distanceThreshold) {
        List<Complex> complexList = points.stream().map(Complex::new).collect(Collectors.toList());
        List<Complex> grouped = new LinkedList<>();

        while (!complexList.isEmpty()) {
            Complex head = complexList.get(0);
            complexList = complexList.subList(1, complexList.size());
            grouped.add(concatComplexes(extractNeighbours(Arrays.asList(head), complexList, distanceThreshold)));
        }

        return new ComplexStream(grouped);
    }

    static public Complex concatComplexes(Stream<Complex> complexStream) {
        return complexStream.reduce(new Complex(), Complex::add);
    }

    static public Stream<Complex> extractNeighbours(List<Complex> extractees, List<Complex> toExtract, double distanceThreshold) {
        List<Complex> neighbours = extractees.stream()
                .flatMap(x -> toExtract.stream().filter(y -> x.distance(y) < distanceThreshold))
                .collect(Collectors.toList());

        if (neighbours.isEmpty())
            return Stream.empty();

        toExtract.removeAll(neighbours);

        return Stream.concat(extractees.stream(), extractNeighbours(neighbours, toExtract, distanceThreshold));
    }
}
