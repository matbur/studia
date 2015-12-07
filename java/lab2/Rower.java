/**
 * Created by matbur on 11/30/15.
 */

/**
 * Klasa implementuje Pojazd Rower.
 */
public class Rower extends Pojazd {
    /**
     * Typ roweru, np. gorski, miejski, tandem.
     */
    private String typ;

    /**
     * Konstruktor tworzy Rower z typem "unknown".
     */
    Rower() {
        super();
        typ = "unknown";
    }

    /**
     * Konstruktor z typem.
     *
     * @param typ
     */
    Rower(String typ) {
        super();
        this.typ = typ;
    }

    /**
     * Konstruktor z predkoscia.
     *
     * @param predkosc
     */
    Rower(double predkosc) {
        super(predkosc);
        typ = "unknown";
    }

    /**
     * Konstruktor z typem i predkoscia.
     *
     * @param typ
     * @param predkosc
     */
    Rower(String typ, double predkosc) {
        super(predkosc);
        this.typ = typ;
    }

    /**
     * Konstruktor z typem, predkoscia i pozycja.
     *
     * @param typ
     * @param predkosc
     * @param x
     * @param y
     */
    Rower(String typ, double predkosc, double x, double y) {
        super(predkosc, x, y);
        this.typ = typ;
    }

    /**
     * Metoda zwraca obiekt z wypelnionymi polami.
     *
     * @return
     */
    public static Rower nowyRower() {
        String typ = PobierzDane.pobierzString("Jaki jest typ roweru?");
        double predkosc = PobierzDane.pobierzDouble("Jaka jest predkosc roweru?");
        return new Rower(typ, predkosc);
    }

    /**
     * Metoda zwraca typ roweru.
     *
     * @return
     */
    public String getTyp() {
        return typ;
    }

    /**
     * Metoda zwraca pola obiektu.
     *
     * @return
     */
    @Override
    public String toString() {
        return "rower: typ=" + typ + ", " + super.toString();
    }
}
