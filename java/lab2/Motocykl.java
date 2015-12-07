/**
 * Created by matbur on 12/1/15.
 */

/**
 * Klasa implementuje Pojazd Motocykl.
 */
public class Motocykl extends Pojazd {
    /**
     * Typ motocykla, np. scigacz, skuter, naked.
     */
    private String typ;

    /**
     * Numer tablicy rejestracyjnej.
     */
    private String tab_rejestr;

    /**
     * Konstruktor tworzy Motocykl z typem i nr rejestracyjnym unknown.
     */
    Motocykl() {
        super();
        typ = "unknown";
        tab_rejestr = "unknown";
    }

    /**
     * Konstruktor z typem.
     *
     * @param typ
     */
    Motocykl(String typ) {
        super();
        this.typ = typ;
    }

    /**
     * Konstruktor z predkoscia.
     *
     * @param predkosc
     */
    Motocykl(double predkosc) {
        super(predkosc);
        typ = "unknown";
        tab_rejestr = "unknown";
    }

    /**
     * Konstruktor z typem, nr rejestracyknym i predkoscia.
     *
     * @param typ
     * @param tab_rejestr
     * @param predkosc
     */
    Motocykl(String typ, String tab_rejestr, double predkosc) {
        super(predkosc);
        this.typ = typ;
        this.tab_rejestr = tab_rejestr;
    }

    /**
     * Konstruktor z typem, nr rejestracyknym, predkoscia i pozyzja.
     *
     * @param typ
     * @param tab_rejestr
     * @param predkosc
     * @param x
     * @param y
     */
    Motocykl(String typ, String tab_rejestr, double predkosc, double x, double y) {
        super(predkosc, x, y);
        this.typ = typ;
        this.tab_rejestr = tab_rejestr;
    }

    /**
     * Metoda zwraca obiekt z wypelnionymi polami.
     *
     * @return
     */
    public static Motocykl nowyMotocykl() {
        String typ = PobierzDane.pobierzString("Jaki typ motocykla?");
        String tab_rejestr = PobierzDane.pobierzString("Jaki jest nr rejestracyjny");
        double predkosc = PobierzDane.pobierzDouble("Jaka jest predkosc motocykla?");
        return new Motocykl(typ, tab_rejestr, predkosc);
    }

    /**
     * Metoda zwraca nr rejestracyjny motocykla.
     *
     * @return
     */
    public String getTab_rejestr() {
        return tab_rejestr;
    }

    /**
     * Metoda zwraca typ motocykla.
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
        return "motocykl: typ=" + typ + ", tab_rej=" + tab_rejestr + ", " + super.toString();
    }
}
