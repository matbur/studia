/**
 * Created by matbur on 11.01.16.
 */

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    Canvas canvas;
    Setup setup;

    public Frame() {
        super("SNAILS");

        int setupWidth = 150;
        setup = new Setup(setupWidth);
        setup.setPreferredSize(new Dimension(setupWidth, 600));
        add(setup, BorderLayout.LINE_START);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(600, 600));
        add(canvas);


        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setVisible(true);
    }
}
