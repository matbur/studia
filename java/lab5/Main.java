/**
 * Created by matbur on 11.01.16.
 */

import javax.swing.*;
import java.util.Random;

public class Main {
    static int w;
    static int h;
    static int n;
    static Cells cells;
    static Snails snails;
    static Sun sun;
    static Frame frame;

    private static Random r;

    public static void main(String[] args) {
        r = new Random();

        SwingUtilities.invokeLater(() -> {
            frame = new Frame();
        });

    }

    synchronized static public int getRandom(int max) {
        return r.nextInt(max);
    }

    static void createAll() {
        cells = new Cells(w, h);
        snails = new Snails(w, h, n);
        sun = new Sun();
    }
}
