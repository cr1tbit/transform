package com.psuwala.image;

import com.psuwala.image.util.ShowUtil;
import org.junit.Ignore;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    @Ignore
    public void showTestImagexD() throws IOException, InterruptedException {
        JFrame jFrame = ShowUtil.showImage(ImageIO.read(AppTest.class.getResourceAsStream("/002112.bmp")));
        while(jFrame.isVisible()){
            Thread.sleep(100);
        }
    }
}
