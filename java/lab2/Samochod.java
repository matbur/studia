/**
 * Created by matbur on 11/30/15.
 */

/**
 * Klasa implementuje Pojazd Samochod.
 */
public class Samochod extends Pojazd {
    /**
     * Numer tablicy rejestracyjnej.
     */
    private String tab_rejestr;

    /**
     * Konstruktor tworzy Samochod nr rejestracyjnym unknown.
     */
    Samochod() {
        super();
        tab_rejestr = "unknown";
    }

    /**
     * Konstruktor z nr rejestracyjnym.
     *
     * @param tab_rejestr
     */
    Samochod(String tab_rejestr) {
        super();
        this.tab_rejestr = tab_rejestr;
    }

    /**
     * Konstruktor z predkoscia.
     *
     * @param predkosc
     */
    Samochod(double predkosc) {
        super(predkosc);
        tab_rejestr = "unknown";
    }

    /**
     * Konstruktor z nr rejestracyjnym i predkoscia.
     *
     * @param tab_rejestr
     * @param predkosc
     */
    Samochod(String tab_rejestr, double predkosc) {
        super(predkosc);
        this.tab_rejestr = tab_rejestr;
    }

    /**
     * Konstruktor z nr rejestracyknym, predkoscia i pozyzja.
     *
     * @param tab_rejestr
     * @param predkosc
     * @param x
     * @param y
     */
    Samochod(String tab_rejestr, double predkosc, double x, double y) {
        super(predkosc, x, y);
        this.tab_rejestr = tab_rejestr;
    }

    /**
     * Metoda zwraca obiekt z wypelnionymi polami.
     *
     * @return
     */
    public static Samochod nowySamochod() {
        String tab_rejestr = PobierzDane.pobierzString("Jaki jest nr rejestracyjny");
        double predkosc = PobierzDane.pobierzDouble("Jaka jest predkosc samochodu?");
        return new Samochod(tab_rejestr, predkosc);
    }

    /**
     * Metoda zwraca nr rejestracyjny samochodu.
     *
     * @return
     */
    public String getTab_rejestr() {
        return tab_rejestr;
    }

    /**
     * Metoda zwraca pola obiektu.
     *
     * @return
     */
    @Override
    public String toString() {
        return "samochod: tab_rej=" + tab_rejestr + ", " + super.toString();
    }
}
