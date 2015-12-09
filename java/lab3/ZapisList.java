/**
 * Created by matbur on 09.12.15.
 */

import javax.swing.JOptionPane;
import java.util.Iterator;
import java.util.LinkedList;

public class ZapisList extends LinkedList<Zapis> {
    public void dodajNowy(StudentList studentList, GrupaList grupaList) {
        if (studentList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "brak studentow");
            return;
        } else if (grupaList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "brak grup");
            return;
        }

        Student student = studentList.wybierz();
        if (student == null) {
            return;
        }

        Grupa grupa = grupaList.wybierz();
        if (grupa == null) {
            return;
        }

        Zapis zapis = new Zapis(student, grupa);
        if (contains(zapis)) {
            JOptionPane.showMessageDialog(null, "Taki zapis juz istnieje");
            return;
        }

        add(zapis);
    }

    public String pokazStudentow(StudentList studentList) {
        if (studentList.isEmpty()) {
            return "brak studentow";
        } else if (isEmpty()) {
            return "brak zapisow";
        }

        String string = "Lista zapisow wg. studentow:\n\n";
        for (Student student : studentList) {
            string += student + ":\n";
            for (Zapis zapis : this) {
                if (student.equals(zapis.getStudent())) {
                    string += "    - " + zapis.getGrupa() + "\n";
                }
            }
            string += "\n";
        }
        return string;
    }

    public void wyswieltStudentow(StudentList studentList) {
        JOptionPane.showMessageDialog(null, pokazStudentow(studentList));
    }

    public String pokazGrupy(GrupaList grupaList) {
        if (grupaList.isEmpty()) {
            return "brak grup";
        } else if (isEmpty()) {
            return "brak zapisow";
        }

        String string = "Lista zapisow wg. grup:\n\n";
        for (Grupa grupa : grupaList) {
            string += grupa + ":\n";
            for (Zapis zapis : this) {
                if (grupa.equals(zapis.getGrupa())) {
                    string += "    - " + zapis.getStudent() + "\n";
                }
            }
            string += "\n";
        }
        return string;
    }

    public void wyswieltGrupy(GrupaList grupaList) {
        JOptionPane.showMessageDialog(null, pokazGrupy(grupaList));
    }

    public Zapis wybierz() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(null, "brak zapisow");
            return null;
        }

        String menu = "Podaj nr grupy:\n" + this + "\n0 - powrot";
        int n = PobierzDane.pobierzInt(menu);

        if (n == 0) {
            System.out.println("null");
            return null;
        }

        try {
            return get(n - 1);
        } catch (java.lang.IndexOutOfBoundsException e) {
            return wybierz();
        }
    }

    public void usun() {
        Zapis zapis = wybierz();
        if (zapis == null) {
            return;
        }
        remove(zapis);
    }

    public void usun(Student student) {
        if (student == null) {
            return;
        }

        ZapisList toRemoveZapisList = new ZapisList();

        for (Zapis zapis : this) {
            if (zapis.getStudent().equals(student))
                toRemoveZapisList.add(zapis);
        }
        removeAll(toRemoveZapisList);
    }

    public void usun(Grupa grupa) {
        if (grupa == null) {
            return;
        }

        ZapisList toRemoveZapisList = new ZapisList();

        for (Zapis zapis : this) {
            if (zapis.getGrupa().equals(grupa))
                toRemoveZapisList.add(zapis);
        }
        removeAll(toRemoveZapisList);
    }

    public boolean contains(Zapis zapis) {
        Iterator<Zapis> iterator = iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals(zapis)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "brak zapisow";
        }

        String string = "";
        for (int i = 0; i < size(); i++) {
            string += String.format("%d) %s\n", i + 1, get(i));
        }
        return string;
    }
}
