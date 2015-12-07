/**
 * Created by matbur on 12/1/15.
 */

import javax.swing.*;

/**
 * Klasa jest wrapperem do JOptionPane
 */

public abstract class PobierzDane {

    /**
     * Metoda pobiera lancuch znakow.
     *
     * @param pytanie
     * @return pobrany lancuch znakow
     */
    public static String pobierzString(String pytanie) {
        String odp = JOptionPane.showInputDialog(null, pytanie);

        if (odp == null) {
            return PobierzDane.pobierzString(pytanie);
        }

        return odp;
    }

    /**
     * Metoda pobiera liczbe typy double
     *
     * @param pytanie
     * @return pobrana liczba
     */
    public static double pobierzDouble(String pytanie) {
        String odp = PobierzDane.pobierzString(pytanie);
        double odpowiedz;

        try {
            odpowiedz = Double.parseDouble(odp);
        } catch (NumberFormatException err) {
            odpowiedz = PobierzDane.pobierzDouble(pytanie);
        }

        return odpowiedz;
    }

    /**
     * Metoda pobiera double i konwertuje na inta
     *
     * @param pytanie
     * @return pobrany int
     */
    public static int pobierzInt(String pytanie) {
        return (int) PobierzDane.pobierzDouble(pytanie);
    }
}
