/**
 * Created by matbur on 11.01.16.
 */

import java.util.Arrays;


public class Leaf extends Thread {
    int speed;
    final Cell[][] cells;

    public Leaf(int w, int h) {
        speed = 0;
        cells = new Cell[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    @Override
    public void run() {
        while (true) {
//            System.out.println(this);

            synchronized (cells) {
                for (Cell[] cell : cells) {
                    for (Cell c : cell) {
                        c.increment();
                    }
                }
            }
            try {
                sleep((100 - speed) * 10);
            } catch (InterruptedException ignored) {
            }
            Main.frame.canvas.repaint();
        }
    }

    @Override
    public String toString() {
        return "Leaf{" +
                "speed=" + speed +
                ", cells=" + Arrays.toString(cells) +
                '}';
    }
}
