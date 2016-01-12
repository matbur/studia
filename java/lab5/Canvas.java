/**
 * Created by matbur on 11.01.16.
 */

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    int w, h;

    public Canvas(int w, int h) {
        this.w = w;
        this.h = h;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int W = getWidth() / w;
        int H = getHeight() / h;

        synchronized (Main.leaf.cells) {
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    g.setColor(Main.leaf.cells[i][j].getColor());
                    g.fillRect(j * W, i * H, W, H);
                }
            }
            g.setColor(Color.ORANGE);
            for (Snail snail : Main.snails) {
                g.fillOval(snail.x * W, snail.y * H, W, H);
            }
//            g.fillOval(Main.snail.x * W, Main.snail.y * H, W, H);
        }

    }
}
