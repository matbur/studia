/**
 * Created by matbur on 11.01.16.
 */


public class Sun extends Thread {
    double speed;
    boolean doWait;

    public Sun() {
        speed = .66;
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

            for (Cell[] cells : Main.cells.cells) {
                for (Cell c : cells) {
                    synchronized (c) {
                        c.increment();
                    }
                }
            }
            try {
                sleep((int) (1000 / speed));
            } catch (InterruptedException ignored) {
            }
            Main.frame.canvas.repaint();
        }
    }
}
