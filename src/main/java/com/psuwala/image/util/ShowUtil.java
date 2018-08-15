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

    public static JFrame showImage(BufferedImage image, double scale) {
        JFrame jFrame = new JFrame("marzanna");

        JPanel pane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ((Graphics2D) g).scale(scale, scale);
                g.drawImage(image, 0, 0, null);
            }
        };

        jFrame.setSize((int) (image.getWidth() * scale), (int) (image.getHeight() * scale) + 20);
        jFrame.setResizable(false);
        jFrame.add(pane);
        jFrame.getContentPane().setPreferredSize(new Dimension((int) (image.getWidth() * scale), (int) (image.getHeight() * scale)));
        jFrame.setUndecorated(false);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        return jFrame;
    }

}
