package com.psuwala.image.streams;

import com.psuwala.image.point.Complex;
import com.psuwala.image.point.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Math.round;

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

    public BufferedImage draw() {
        int minX = points.stream().map(Point::getX).min(Double::compareTo).orElse(0.0).intValue();
        int minY = points.stream().map(Point::getY).min(Double::compareTo).orElse(0.0).intValue();

        int maxX = points.stream().map(Point::getX).min(Double::compareTo).orElse(0.0).intValue();
        int maxY = points.stream().map(Point::getY).min(Double::compareTo).orElse(0.0).intValue();

        BufferedImage bufferedImage = new BufferedImage(maxX - minX + 1, maxY - minY + 1, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.setPaint(new Color(255, 255, 255));
        g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());

        points.stream().forEach(x ->
                bufferedImage.setRGB(
                        (int)round(x.getX() - minX),
                        (int)round(x.getY() - minY),
                        new Color(0).getRGB()
                )
        );

        return bufferedImage;
    }

    static private Complex concatComplexes(Stream<Complex> complexStream) {
        return complexStream.reduce(new Complex(), Complex::add);
    }

    static private Stream<Complex> extractNeighbours(List<Complex> extractees, List<Complex> toExtract, double distanceThreshold) {
        List<Complex> neighbours = extractees.stream()
                .flatMap(x -> toExtract.stream().filter(y -> x.distance(y) < distanceThreshold))
                .collect(Collectors.toList());

        if (neighbours.isEmpty())
            return Stream.empty();

        toExtract.removeAll(neighbours);

        return Stream.concat(extractees.stream(), extractNeighbours(neighbours, toExtract, distanceThreshold));
    }
}
