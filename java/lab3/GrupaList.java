/**
 * Created by matbur on 09.12.15.
 */

import javax.swing.*;
import java.util.ArrayList;

public class GrupaList extends ArrayList<Grupa> {
    public void dodajNowy(JFrame frame) {
        String nazwa = PobierzDane.pobierzString("Podaj nazwe grupy:", frame);
        if (nazwa == null) {
            return;
        }

        Grupa grupa = new Grupa(nazwa);
        if (contains(grupa)) {
            JOptionPane.showMessageDialog(frame, "Taka grupa juz istnieje");
            return;
        }

        add(grupa);
    }


    public void wyswietl(JFrame frame) {
        String string;
        if (isEmpty()) {
            string = "brak grup";
        } else {
            string = "Lista grup:\n" + this;
        }

        JOptionPane.showMessageDialog(frame, string);
    }

    public Grupa wybierz(JFrame frame) {
        if (isEmpty()) {
            return null;
        }

        String menu = "Podaj nr grupy:\n" + this + "\n0 - powrot";
        int n = PobierzDane.pobierzInt(menu, frame);

        if (n == 0) {
            return null;
        }

        try {
            return get(n - 1);
        } catch (java.lang.IndexOutOfBoundsException e) {
            return wybierz(frame);
        }
    }

    public Grupa usun(JFrame frame) {
        Grupa grupa = wybierz(frame);
        remove(grupa);
        return grupa;
    }

    public boolean contains(Grupa grupa) {
        for (Grupa grupa1 : this) {
            if (grupa1.equals(grupa)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "brak grup";
        }

        String string = "";
        for (int i = 0; i < size(); i++) {
            string += String.format("%d) %s\n", i + 1, get(i));
        }

        return string;
    }
}
