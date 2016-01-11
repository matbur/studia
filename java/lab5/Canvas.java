/**
 * Created by matbur on 11.01.16.
 */

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    int h, w;

    public Canvas(int h, int w) {
        this.h = h;
        this.w = w;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int W = getWidth() / w;
        int H = getHeight() / h;

        synchronized (Main.leaf.cells) {
            for (int i = 0; i < h; i++) {
                System.out.println(H * i);
                for (int j = 0; j < w; j++) {
                    g.setColor(Main.leaf.cells[i][j].color);
                    g.fillRect(j * W, i * H, W, H);
                }
            }
        }

    }
}
