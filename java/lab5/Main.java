/**
 * Created by matbur on 11.01.16.
 */

import javax.swing.*;
import java.util.Random;

public class Main {
    static Leaf leaf;
    static Frame frame;
    static Snails snails;

    private static Random r;

    public static void main(String[] args) {
        r = new Random();

        int w = 100;
        int h = 100;
        int n = 100;

        leaf = new Leaf(w, h);
        snails = new Snails(w, h, n);

        SwingUtilities.invokeLater(() -> {
            frame = new Frame(w, h);
        });

        leaf.start();
        snails.forEach(Snail::start);
    }

    static public int getRandom(int max) {
        return r.nextInt(max);
    }
}
