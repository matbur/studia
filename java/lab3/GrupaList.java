/**
 * Created by matbur on 09.12.15.
 */

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class GrupaList extends ArrayList<Grupa> {
    public void dodajNowy() {
        String nazwa = PobierzDane.pobierzString("Podaj nazwe grupy:\n0 - powrot");
        if (nazwa.equals("0")) {
            return;
        }

        Grupa grupa = new Grupa(nazwa);
        if (contains(grupa)) {
            JOptionPane.showMessageDialog(null, "Taka grupa juz istnieje");
            return;
        }

        add(grupa);
    }


    public void wyswietl() {
        JOptionPane.showMessageDialog(null, "Lista grup:\n" + this);
    }

    public Grupa wybierz() {
        if (isEmpty()) {
            return null;
        }

        String menu = "Podaj nr grupy:\n" + this + "\n0 - powrot";
        int n = PobierzDane.pobierzInt(menu);

        if (n == 0) {
            return null;
        }

        try {
            return get(n - 1);
        } catch (java.lang.IndexOutOfBoundsException e) {
            return wybierz();
        }
    }

    public Grupa usun() {
        Grupa grupa = wybierz();
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
