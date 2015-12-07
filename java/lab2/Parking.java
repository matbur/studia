/**
 * Created by matbur on 11/30/15.
 */

import javax.swing.*;

/**
 * Klasa glowna implementujaca parking
 * Na parkingu znajduja sie pojazdy: rowery, motocykle lub samochody
 */

public class Parking {

    /**
     * Maxymalna ilosc miejsc na parkingu.
     */
    private final int MAX;

    /**
     * Tablica referencji na wszystkie pojazdy.
     */
    private final Pojazd[] tab;

    /**
     * Aktualna ilosc pojazdow.
     */
    private int ilosc_pojazdow;

    /**
     * Konstruktor domniemany tworzy parking na 100 miejsc.
     */
    Parking() {
        MAX = 100;
        tab = new Pojazd[MAX];
        ilosc_pojazdow = 0;
    }

    /**
     * Konstruktor tworzy parking na dowolna ilosc miejsc.
     *
     * @param max maxymalna ilosc miejsc
     */
    Parking(int max) {
        MAX = max;
        tab = new Pojazd[MAX];
        ilosc_pojazdow = 0;
    }

    /**
     * Glowna metoda. Tworzy parking i pokazuje menu.
     *
     * @param args
     */
    public static void main(String[] args) {
        Parking.pokazMenu(Parking.nowyParking());
    }

    /**
     * Metoda tworzy parking o podanej ilosci miejsc.
     *
     * @return
     */
    public static Parking nowyParking() {
        int max = 0;
        do {
            max = PobierzDane.pobierzInt("Ile miejsc ma miec ten parking? [1..oo]");
        } while (max < 1);

        return new Parking(max);
    }

    /**
     * Metoda zawiera glowna petle z menu.
     *
     * @param p
     */
    public static void pokazMenu(Parking p) {
        String menu = "M E N U\n" +
                "1 - dodaj nowy pojazd\n" +
                "2 - usun pojazd\n" +
                "3 - pokaz parking \n\n" +
                "0 - zakoncz program";

        while (true) {
            switch (PobierzDane.pobierzInt(menu)) {
                case 1:
                    p.dodajPojazd();
                    p.pokazParking();
                    break;
                case 2:
                    p.usunPojazd();
                    p.pokazParking();
                    break;
                case 3:
                    p.pokazParking();
                    break;
                case 0:
                    System.exit(0);
            }
        }

    }

    /**
     * Metoda zwraca ilosc pojazdow.
     *
     * @return
     */
    public int getIlosc_pojazdow() {
        return ilosc_pojazdow;
    }

    /**
     * Metoda zwraca rozmiar parkingu.
     *
     * @return
     */
    public int getMAX() {
        return MAX;
    }

    /**
     * Metoda pokazuje okienko z parkingiem.
     */
    public void pokazParking() {
        JOptionPane.showMessageDialog(null, this.toString());
    }

    /**
     * Metoda dodaje nowe pojazdy na parking.
     */
    public void dodajPojazd() {
        if (MAX - ilosc_pojazdow < 1) {
            JOptionPane.showMessageDialog(null, "Brak wolnych miejsc");
            return;
        }

        Pojazd p;

        String menu = "Podaj typ pojazdu:\n" +
                "1 - rower\n" +
                "2 - motocykl\n" +
                "3 - samochod\n\n" +
                "0 - powrot";

        switch (PobierzDane.pobierzInt(menu)) {
            case 1:
                p = Rower.nowyRower();
                break;
            case 2:
                p = Motocykl.nowyMotocykl();
                break;
            case 3:
                p = Samochod.nowySamochod();
                break;
            case 0:
                return;
            default:
                this.dodajPojazd();
                return;
        }

        tab[ilosc_pojazdow] = p;
        ilosc_pojazdow += 1;
    }

    /**
     * Metoda usuwa podane pojazdy z parkingu.
     */
    public void usunPojazd() {
        if (ilosc_pojazdow < 1) {
            JOptionPane.showMessageDialog(null, "Ten parking jest pusty");
            return;
        }

        String menu = "Ktory pojazd chcesz usunac?\n";

        for (int i = 0; i < ilosc_pojazdow; i++) {
            if (tab[i] != null) {
                menu += String.format("\n%d - %s", i + 1, tab[i]);
            }
        }

        menu += "\n\n0 - powrot";
        int n = PobierzDane.pobierzInt(menu);

        if (n < 0) {
            JOptionPane.showMessageDialog(null, "Numer pojazdu musi byc dodatni");
            this.usunPojazd();
            return;
        } else if (n > ilosc_pojazdow) {
            JOptionPane.showMessageDialog(null, "Nie ma pojazdu o tym numerze");
            this.usunPojazd();
            return;
        } else if (n == 0) {
            return;
        }

        n -= 1;     //bo indexowanie tablicy zaczyna sie od zera

        for (int i = n; i < ilosc_pojazdow; i++) {
            tab[i] = tab[(i + 1) % MAX];
        }
        tab[ilosc_pojazdow - 1] = null;
        ilosc_pojazdow -= 1;
    }

    /**
     * Metoda zwraca obiekt Parking jako String.
     *
     * @return
     */
    @Override
    public String toString() {
        String tekst = "PARKING\n\n" +
                "ilosc miejsc:\n        " +
                "wolnych: %d\n        " +
                "zajetych: %d\n";
        String tresc = String.format(tekst, MAX - ilosc_pojazdow, ilosc_pojazdow);

        for (int i = 0; i < ilosc_pojazdow; i++) {
            if (tab[i] != null) {
                tresc += String.format("\n %d) %s", i + 1, tab[i]);
            }
        }

        return tresc;
    }
}
