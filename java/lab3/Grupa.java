/**
 * Created by matbur on 07.12.15.
 */

import javax.swing.*;
import java.util.Comparator;
import java.util.List;

public class Grupa {
    private String nazwa;

    public Grupa() {
        nazwa = "unknown";
    }

    public Grupa(String nazwa) {
        this.nazwa = nazwa;
    }

    public static Grupa nowaGrupa() {
        String nazwa = PobierzDane.pobierzString("Podaj nazwe grupy:");
        return new Grupa(nazwa);
    }

    public static String pokazGrupy(List<Grupa> grupaList) {
        if (grupaList.isEmpty()) {
            return "Brak grup";
        }

        String string = "";
        for (int i = 0; i < grupaList.size(); i++) {
            string += String.format("%d) %s\n", i + 1, grupaList.get(i));
        }

        return string;
    }

    public static void wyswietlGrupy(List<Grupa> grupaList) {
        JOptionPane.showMessageDialog(null,
                "Lista grup:\n" + Grupa.pokazGrupy(grupaList));
    }

    public static Grupa wybierzGrupe(List<Grupa> grupaList) {
        if (grupaList.isEmpty()) {
            return null;
        }

        String menu = "Podaj nr grupy:\n" + pokazGrupy(grupaList) + "\n0 - powrot";
        int n = PobierzDane.pobierzInt(menu);

        if (n == 0) {
            return null;
        }

        try {
            return grupaList.get(n - 1);
        } catch (Exception e) {
            return wybierzGrupe(grupaList);
        }
    }

    public static Grupa usunGrupe(List<Grupa> grupaList) {
        Grupa grupa = wybierzGrupe(grupaList);
        grupaList.remove(grupa);
        return grupa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public boolean equals(Grupa grupa) {
        return nazwa.equals(grupa.nazwa);
    }

    @Override
    public String toString() {
        return nazwa;
    }
}

class GrupaComparable implements Comparator<Grupa> {

    @Override
    public int compare(Grupa o1, Grupa o2) {
        return o1.getNazwa().compareToIgnoreCase(o2.getNazwa());
    }
}
