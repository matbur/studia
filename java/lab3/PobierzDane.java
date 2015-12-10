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
    public static String pobierzString(String pytanie, JFrame frame) {
        return JOptionPane.showInputDialog(frame, pytanie);
    }

    /**
     * Metoda pobiera liczbe typy double
     *
     * @param pytanie
     * @return pobrana liczba
     */
    public static double pobierzDouble(String pytanie, JFrame frame) {
        String odp = PobierzDane.pobierzString(pytanie, frame);
        double odpowiedz;

        try {
            return Double.parseDouble(odp);
        } catch (NumberFormatException err) {
            return PobierzDane.pobierzDouble(pytanie, frame);
        } catch (NullPointerException err) {
            return 0;
        }
    }

    /**
     * Metoda pobiera double i konwertuje na inta
     *
     * @param pytanie
     * @return pobrany int
     */
    public static int pobierzInt(String pytanie, JFrame frame) {
        return (int) PobierzDane.pobierzDouble(pytanie, frame);
    }
}
