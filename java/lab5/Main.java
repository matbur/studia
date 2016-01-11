/**
 * Created by matbur on 11.01.16.
 */

import javax.swing.*;

public class Main {
    static Leaf leaf;
    static Frame frame;
    static int w, h, n;

    public static void main(String[] args) {
        w = 20;
        h = 10;
        leaf = new Leaf(h, w);
        SwingUtilities.invokeLater(() -> {
            frame = new Frame(h, w);
        });
    }
}
