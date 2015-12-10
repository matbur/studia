/**
 * Created by matbur on 09.12.15.
 */

import javax.swing.*;
import java.util.Iterator;
import java.util.LinkedList;

public class ZapisList extends LinkedList<Zapis> {
    public void dodajNowy(StudentList studentList, GrupaList grupaList, JFrame frame) {
        if (studentList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "brak studentow");
            return;
        }

        if (grupaList.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "brak grup");
            return;
        }

        Student student = studentList.wybierz(frame);
        if (student == null) {
            return;
        }

        Grupa grupa = grupaList.wybierz(frame);
        if (grupa == null) {
            return;
        }

        Zapis zapis = new Zapis(student, grupa);
        if (contains(zapis)) {
            JOptionPane.showMessageDialog(frame, "Taki zapis juz istnieje");
            return;
        }

        add(zapis);
    }

    public String pokazStudentow(StudentList studentList) {
        if (studentList.isEmpty()) {
            return "brak studentow";
        }

        if (isEmpty()) {
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

    public void wyswieltStudentow(StudentList studentList, JFrame frame) {
        JOptionPane.showMessageDialog(frame, pokazStudentow(studentList));
    }

    public String pokazGrupy(GrupaList grupaList) {
        if (grupaList.isEmpty()) {
            return "brak grup";
        }

        if (isEmpty()) {
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

    public void wyswieltGrupy(GrupaList grupaList, JFrame frame) {
        JOptionPane.showMessageDialog(frame, pokazGrupy(grupaList));
    }

    public Zapis wybierz(JFrame frame) {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(frame, "brak zapisow");
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

    public void usun(JFrame frame) {
        Zapis zapis = wybierz(frame);
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
