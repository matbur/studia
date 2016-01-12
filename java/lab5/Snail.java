/**
 * Created by matbur on 11.01.16.
 */

public class Snail extends Thread {
    int greediness;
    int x;
    int y;
    int w;
    int h;
    double speed;
    int[][] neighbors;
    boolean doWait;

    public Snail(int w, int h) {
        this.w = w;
        this.h = h;

        neighbors = new int[4][3];

        greediness = 8;
        speed = 3;

        synchronized (Main.cells.cells) {
            do {
                x = Main.getRandom(w);
                y = Main.getRandom(h);
            } while (Main.cells.cells[y][x].isSnail());

            Main.cells.cells[y][x].toggleIsSnail();
        }

        doWait = false;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                while (doWait) {
                    try {
                        wait();
                    } catch (InterruptedException ignored) {
                    }
                }
            }

            synchronized (Main.cells.cells[y][x]) {
                Main.cells.cells[y][x].decrement(greediness);
                Main.cells.cells[y][x].toggleIsSnail();
            }

            updateNeighbors();

            int max_y = neighbors[0][0];
            int max_x = neighbors[0][1];
            int max = neighbors[0][2];

            for (int[] n : neighbors) {
                synchronized (Main.cells.cells[n[0]][n[1]]) {

                    boolean tempIsSnail = Main.cells.cells[n[0]][n[1]].isSnail();
                    if (!tempIsSnail && (n[2] > max || n[2] == max && Main.getRandom(2) == 0)) {
                        max_y = n[0];
                        max_x = n[1];
                        max = n[2];
                    }
                }
            }
            y = max_y;
            x = max_x;
            synchronized (Main.cells.cells[y][x]) {
                Main.cells.cells[y][x].toggleIsSnail();
            }

            try {
                sleep((int) (1000 / speed));
            } catch (InterruptedException ignored) {
            }
            Main.frame.canvas.repaint();
        }
    }

    void updateNeighbors() {
        neighbors[0][0] = y;
        neighbors[0][1] = x + 1;
        neighbors[1][0] = y + 1;
        neighbors[1][1] = x;
        neighbors[2][0] = y;
        neighbors[2][1] = x - 1;
        neighbors[3][0] = y - 1;
        neighbors[3][1] = x;

        for (int i = 0; i < 4; i++) {
            try {
                synchronized (Main.cells.cells[neighbors[i][0]][neighbors[i][1]]) {
                    neighbors[i][2] = Main.cells.cells[neighbors[i][0]][neighbors[i][1]].getValue();
                }
            } catch (java.lang.ArrayIndexOutOfBoundsException ignored) {
                neighbors[i][0] = 0;
                neighbors[i][1] = 0;
                neighbors[i][2] = -1;
            }
        }
    }
}
