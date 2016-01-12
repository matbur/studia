/**
 * Created by matbur on 11.01.16.
 */

import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    int w, h;
    boolean isReady;

    public Canvas() {
        isReady = false;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (isReady) {
            int W = getWidth() / w;
            int H = getHeight() / h;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    synchronized (Main.cells.cells[i][j]) {
                        g.setColor(Main.cells.cells[i][j].getColor());
                        g.fillRect(j * W, i * H, W, H);
                    }
                }
            }
            g.setColor(Color.ORANGE);
            for (Snail snail : Main.snails) {
                synchronized (snail) {
                    g.fillOval(snail.x * W, snail.y * H, W, H);
                }
            }
        }
    }

    public void updateWH(){
        w = Main.w;
        h = Main.h;
    }
}
