/**
 * Created by matbur on 11.01.16.
 */

import java.awt.*;
import java.util.Random;

public class Cell {
    Color color;
    int value, max;

    public Cell() {
        max = 9;
        value = new Random().nextInt(max);
        updateColor();
    }

    public void increment() {
        if (value < max) {
            value++;
        }
        updateColor();
    }

    public void decrement(int n) {
        if (value - n > 0) {
            value -= n;
        } else {
            value = 0;
        }
        updateColor();
    }

    public void updateColor() {
        color = new Color(0, (int) (255. / max * value), 0);
    }
}
