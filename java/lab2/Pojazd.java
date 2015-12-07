/**
 * Created by matbur on 11/30/15.
 */

/**
 * Klasa stanowi baze pod konkretne pojazdy.
 */
public abstract class Pojazd {
    /**
     * Predkosc pojazdu.
     */
    protected double predkosc;

    /**
     * Aktualna pozycja GPS.
     */
    protected double[] pozycja_gps = new double[2];

    /**
     * Konstruktor domniemany tworzy pojazd z wyzerowanymi danymi.
     */
    Pojazd() {
        predkosc = 0;
        pozycja_gps[0] = 0;
        pozycja_gps[0] = 0;
    }

    /**
     * Konstruktor tworzy Pojazd z wybrana predkoscia.
     *
     * @param predkosc
     */
    Pojazd(double predkosc) {
        this();
        this.predkosc = predkosc;
    }

    /**
     * Konstruktor tworzy Pojazd z predkoscia i pozycja.
     *
     * @param predkosc
     * @param x
     * @param y
     */
    Pojazd(double predkosc, double x, double y) {
        this(predkosc);
        pozycja_gps[0] = x;
        pozycja_gps[1] = y;
    }

    /**
     * Metoda zwraca predkosc pojazdu.
     *
     * @return
     */
    public double getPredkosc() {
        return predkosc;
    }

    /**
     * Metoda pokazuje dane Pojazdu jako String.
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("v=%.1f, poz=(%.1f, %.1f)", predkosc, pozycja_gps[0], pozycja_gps[1]);
    }
}
