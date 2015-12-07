/**
 * Created by matbur on 10/19/15.
 */

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

enum Grades {
    NULL(0),
    NIEDOSTATECZNY(2),
    DOSTATECZNY(3),
    PLUS_DOSTATECZNY(3.5),
    DOBRY(4),
    PLUS_DOBRY(4.5),
    BARDZO_DOBRY(5),
    CELUJACY(5.5);

    private double grade;

    Grades(double g) {
        grade = g;
    }

    public double getGrade() {
        return grade;
    }
}

public class OcenaPWr {
    Grades grade;

    public static void main(String[] args) {

        ArrayList<OcenaPWr> tab = new ArrayList<>();

        loadGrades(tab);
        showMenu(tab);
    }

    OcenaPWr() {
        grade = Grades.NULL;
    }

    OcenaPWr(double d) {
        boolean is_set = false;
        for (Grades g : Grades.values()) {
            if (g.getGrade() == d) {
                grade = g;
                is_set = true;
            }
        }

        if (!is_set) {
            grade = Grades.NULL;
        }
    }

    OcenaPWr(String s) {
        s = s.replace(',', '.').replace(' ', '_');

        boolean is_set = false;
        for (Grades g : Grades.values()) {
            if (String.valueOf(g.getGrade()).equals(s) || g.name().equalsIgnoreCase(s)) {
                grade = g;
                is_set = true;
            }
        }

        if (!is_set) {
            grade = Grades.NULL;
        }
    }

    OcenaPWr(OcenaPWr o) {
        grade = o.getGrade();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        JOptionPane.showMessageDialog(null, "Ocena usunieta");
    }

    public int setGrade() {
        String temp = JOptionPane.showInputDialog("Podaj ocene:");

        if (temp == null)
            return 1;

        temp = temp.replace(',', '.').replace(' ', '_');

        String err = "Bledna ocena!\n\nDopuszczalne oceny to:\n";
        for (Grades g : Grades.values()) {
            if (g.getGrade() != 0.) {
                err += String.format("%s: %.1f\n", g, g.getGrade());
            }
        }

        double dbl = -1;
        try {
            dbl = Double.parseDouble(temp);
        } catch (java.lang.NumberFormatException ignored) {
        }
        boolean is_set = false;

        for (Grades g : Grades.values()) {
            if (g.getGrade() == dbl || g.name().equalsIgnoreCase(temp)) {
                grade = g;
                is_set = true;
            }
        }

        int ans = -1;
        if (!is_set) {
            JOptionPane.showMessageDialog(null, err);
            ans = JOptionPane.showConfirmDialog(null, "Chcesz ponowic probe?");
        }

        switch (ans) {
            case 0:
                setGrade();
                break;
            case 1:
            case 2:
                grade = Grades.NULL;
                break;
        }

        return ans;
    }

    public Grades getGrade() {
        return grade;
    }

    public static void loadGrades(ArrayList<OcenaPWr> tab) {
        int i = 0, n = 0;
        while (true) {
            OcenaPWr o = new OcenaPWr();
            n = o.setGrade();
            if (n != 1 && n != 2) {
                tab.add(o);
                i++;
            } else {
                break;
            }
        }
    }

    public static void showMenu(ArrayList<OcenaPWr> tab) {
        String mes = "Wczytales oceny\n\n";
        mes += "Wybierz opcje:\n";
        mes += "1) dodaj nowa ocene\n";
        mes += "2) sortuj oceny\n";
        mes += "3) wyswietl oceny\n";
        mes += "0) zakoncz program";
        String ans = JOptionPane.showInputDialog(mes);

        switch (ans) {
            case "1":
                OcenaPWr o = new OcenaPWr();
                int n = o.setGrade();
                if (n != 1 && n != 2) {
                    tab.add(o);
                }
                showMenu(tab);
                break;
            case "2":
                Collections.sort(tab, new OcenaPWrComparator());
                JOptionPane.showMessageDialog(null, "Oceny posortowane");
                showMenu(tab);
                break;
            case "3":
                String s = "";
                for (OcenaPWr o2 : tab) {
                    if (o2.getGrade() != Grades.NULL) {
                        s += String.format("%s\n", o2.getGrade().getGrade());
                    }
                }
                JOptionPane.showMessageDialog(null, s);
                showMenu(tab);
                break;
            case "0":
                JOptionPane.showMessageDialog(null, "Milego dnia");
                break;
            default:
                JOptionPane.showMessageDialog(null, "Nie ma takiej opcji");
                showMenu(tab);
        }
    }

    public boolean equals(OcenaPWr o) {
        return getGrade() == o.getGrade();
    }

    public int compareTo(OcenaPWr o) {
        double d = getGrade().getGrade() - o.getGrade().getGrade();
        return (int) (d * 2);                       // '*2' is for compare e.g '3.5' and '4'
    }

    @Override
    public String toString() {
        return String.format("%s", grade);
    }
}

class OcenaPWrComparator implements Comparator<OcenaPWr> {
    @Override
    public int compare(OcenaPWr o1, OcenaPWr o2) {
        return o1.compareTo(o2);
    }
}