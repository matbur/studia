/**
 * Created by matbur on 07.12.15.
 */

import java.util.Comparator;

public class Grupa {
    private String nazwa;

    public Grupa() {
        nazwa = "unknown";
    }

    public Grupa(String nazwa) {
        this.nazwa = nazwa;
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
