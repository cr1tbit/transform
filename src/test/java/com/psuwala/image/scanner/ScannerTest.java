package com.psuwala.image.scanner;

import com.psuwala.image.point.Point;
import com.psuwala.image.streams.PointStream;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;
/**
 * Unit test for simple App.
 */
public class ScannerTest {
    @Test
    public void imageIdentityTest() throws IOException {
        BufferedImage bufferedImage = new BufferedImage(3, 3, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();

        g.setPaint(Color.WHITE);
        g.fillRect(0, 0, 3, 3);

        bufferedImage.setRGB(0, 0, Color.black.getRGB());
        bufferedImage.setRGB(2, 1, Color.black.getRGB());
        bufferedImage.setRGB(1, 2, Color.black.getRGB());

        Scanner scanner = new Scanner(bufferedImage);

        PointStream pointStream = scanner.read(0.5);

        List<Point> list = pointStream.getPoints();

        assertEquals(list.size(), 3);
        assertEquals(list.get(0), new Point(0,0));
        assertEquals(list.get(1), new Point(1,2));
        assertEquals(list.get(2), new Point(2,1));
    }


}
