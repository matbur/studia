/**
 * Created by matbur on 11.01.16.
 */

import java.util.ArrayList;

public class Snails extends ArrayList<Snail> {

    public Snails(int w, int h, int n) {
        for (int i = 0; i < n; i++) {
            add(new Snail(w, h));
        }
    }
}
