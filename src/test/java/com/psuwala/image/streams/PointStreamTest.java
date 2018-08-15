package com.psuwala.image.streams;

import com.psuwala.image.point.Point;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class PointStreamTest {
    @Test
    public void groupingTest() throws IOException, InterruptedException {
        List<Point> list = Arrays.asList(
                new Point(0, 0),
                new Point(1, 2),
                new Point(2, 1)
        );
        PointStream pointStream = new PointStream(list);
        ComplexStream complexStream = pointStream.group(10.0);
        assertEquals(complexStream.getPoints().size(), 1);
        assertEquals(complexStream.getPoints().get(0).getPoints().size(), 3);

        ComplexStream dividedComplexStream = pointStream.group(0.3);
        assertEquals(dividedComplexStream.getPoints().size(), 3);
        assertEquals(dividedComplexStream.getPoints().get(0).getPoints().size(), 1);
        assertEquals(dividedComplexStream.getPoints().get(1).getPoints().size(), 1);
        assertEquals(dividedComplexStream.getPoints().get(2).getPoints().size(), 1);
    }

    @Test
    public void dividedGroupingTest() throws IOException, InterruptedException {
        List<Point> list = Arrays.asList(
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 3),
                new Point(1, 3)
        );
        PointStream pointStream = new PointStream(list);
        ComplexStream complexStream = pointStream.group(1.5);
        assertEquals(complexStream.getPoints().size(), 2);
        assertEquals(complexStream.getPoints().get(0).getPoints().size(), 2);
        assertEquals(complexStream.getPoints().get(1).getPoints().size(), 2);
    }


    @Test
    public void longSnake() throws IOException, InterruptedException {
        List<Point> list = Arrays.asList(
                new Point(0, 0),
                new Point(1, 0),
                new Point(0, 3),
                new Point(1, 3)
        );

    }


}
