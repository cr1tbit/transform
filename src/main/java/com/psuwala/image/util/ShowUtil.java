package com.psuwala.image.util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * TODO: Document this class / interface here
 *
 * @since v7.4
 */
public class ShowUtil {

    public static JFrame showImage(BufferedImage image) {
        JFrame jFrame = new JFrame("marzanna") {
            @Override
            public void paint(Graphics g) {
                super.paintComponents(g);
                g.setColor(Color.red);
                g.fillRect(0, 0, 200, 200);
                g.drawImage(image, 10, 10, null);
            }
        };

        jFrame.setSize(image.getWidth() + 200, image.getHeight() + 200);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        return jFrame;
    }

}
