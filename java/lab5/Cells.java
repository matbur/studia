/**
 * Created by matbur on 12.01.16.
 */
public class Cells {
    Cell[][] cells;

    public Cells(int w, int h) {
        cells = new Cell[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                cells[i][j] = new Cell();
            }
        }
    }
}
