package com.psuwala.image.scanner;

import com.psuwala.image.point.Point;
import com.psuwala.image.streams.PointStream;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.4
 */
public class Scanner {
    BufferedImage processedImage;

    public Scanner(BufferedImage bufferedImage) {
        this.processedImage = bufferedImage;
    }

    public PointStream read(double threshold) {
        return new PointStream(IntStream.range(0, processedImage.getWidth()).boxed().flatMap(
                x -> IntStream.range(0, processedImage.getHeight()).boxed().flatMap(
                        y -> getPower(processedImage.getRGB(x, y)) < threshold ? Stream.of(new Point(x, y)) : Stream.of()
                )
        ).collect(Collectors.toList()));
    }

    public static double getPower(int rgba) {
        Color c = new Color(rgba, false);
        return (c.getBlue() / 255.0 + c.getGreen() / 255.0 + c.getRed() / 255.0) / 3.0;
    }
}
