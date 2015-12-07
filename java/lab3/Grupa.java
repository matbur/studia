/**
 * Created by matbur on 07.12.15.
 */

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

    public static String pokazListeGrup(List<Grupa> grupaList) {
        String string = "";
        for (int i = 0; i < grupaList.size(); i++) {
            string += String.format("%d) %s\n", i + 1, grupaList.get(i));
        }

        return string;
    }

    public static Grupa wybierzGrupe(List<Grupa> grupaList) {
        String menu = "Podaj nr grupy:\n" + pokazListeGrup(grupaList);
        int n = PobierzDane.pobierzInt(menu) - 1;

        if (n < 0 || n >= grupaList.size()) {
            return wybierzGrupe(grupaList);
        }

        return grupaList.get(n);
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
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
