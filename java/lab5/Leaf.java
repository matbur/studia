/**
 * Created by matbur on 11.01.16.
 */

public class Leaf extends Thread {
    int speed;
    final Cell[][] cells;

    public Leaf(int h, int w) {
        speed = 10;
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
            synchronized (cells) {
                for (Cell[] cell : cells) {
                    for (Cell c : cell) {
                        c.increment();
                    }
                }
            }
            try {
                sleep((101 - speed) * 10);
            } catch (InterruptedException ignored) {
            }
            Main.frame.canvas.repaint();
        }
    }
}
