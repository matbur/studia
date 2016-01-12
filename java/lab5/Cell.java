/**
 * Created by matbur on 11.01.16.
 */

import java.awt.*;

public class Cell {
    private Color color;
    private int value, max;
    private boolean isSnail;

    public Cell() {
        max = 9;
        isSnail = false;
        value = Main.getRandom(max);
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

    public Color getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public boolean isSnail() {
        return isSnail;
    }

    public void toggleIsSnail() {
        isSnail = !isSnail;
    }
}
