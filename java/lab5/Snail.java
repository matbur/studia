/**
 * Created by matbur on 11.01.16.
 */

public class Snail extends Thread {
    int greediness, x, y, speed, w, h;
    int[][] neighbours;

    public Snail(int w, int h) {
        this.w = w;
        this.h = h;

        neighbours = new int[4][3];

        greediness = 7;
        speed = 80;

        do {
            x = Main.getRandom(w);
            y = Main.getRandom(h);
        } while (Main.leaf.cells[y][x].isSnail());

        Main.leaf.cells[y][x].toggleIsSnail();
    }

    @Override
    public void run() {
        while (true) {
//            System.out.println(this);

            synchronized (Main.leaf.cells) {
                Main.leaf.cells[y][x].decrement(greediness);

                updateNeighbours();

                int max_y = neighbours[0][0];
                int max_x = neighbours[0][1];
                int max = neighbours[0][2];

                for (int i = 1; i < 4; i++) {
                    int temp_y = neighbours[i][0];
                    int temp_x = neighbours[i][1];
                    int temp = neighbours[i][2];
                    boolean tempIsSnail = Main.leaf.cells[temp_y][temp_x].isSnail();
                    if (!tempIsSnail && (temp > max || temp == max && Main.getRandom(2) == 0)) {
                        max_y = temp_y;
                        max_x = temp_x;
                        max = temp;
                    }
                }

                Main.leaf.cells[y][x].toggleIsSnail();
                y = max_y;
                x = max_x;
                Main.leaf.cells[y][x].toggleIsSnail();
            }

            try {
                sleep((100 - speed) * 10);
            } catch (InterruptedException ignored) {
            }
            Main.frame.canvas.repaint();
        }
    }

    void updateNeighbours() {
        neighbours[0][0] = y;
        neighbours[0][1] = (x + 1) % w;
        neighbours[1][0] = (y + 1) % h;
        neighbours[1][1] = x;
        neighbours[2][0] = y;
        neighbours[2][1] = (x - 1 + w) % w;
        neighbours[3][0] = (y - 1 + h) % h;
        neighbours[3][1] = x;

        for (int i = 0; i < 4; i++) {
            neighbours[i][2] = Main.leaf.cells[neighbours[i][0]][neighbours[i][1]].getValue();
        }
    }

    @Override
    public String toString() {
        return "Snail{" +
                "greediness=" + greediness +
                ", x=" + x +
                ", y=" + y +
                ", speed=" + speed +
                ", w=" + w +
                ", h=" + h +
                '}';
    }
}
