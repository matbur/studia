/**
 * Created by matbur on 09.12.15.
 */

import javax.swing.*;
import java.util.ArrayList;

public class StudentList extends ArrayList<Student> {
    public void dodajNowy(JFrame frame) {
        String nazwisko = PobierzDane.pobierzString("Podaj nazwisko studenta:", frame);
        if (nazwisko == null) {
            return;
        }

        String imie = PobierzDane.pobierzString("Podaj imie studenta:", frame);
        if (imie == null) {
            return;
        }

        int indeks = PobierzDane.pobierzInt("Podaj nr indeksu studenta:\n0 - powrot", frame);
        if (indeks == 0) {
            return;
        }

        Student student = new Student(nazwisko, imie, indeks);
        if (contains(student)) {
            JOptionPane.showMessageDialog(frame, "Taki student juz istnieje");
            return;
        }

        add(student);
    }

    public void wyswietl(JFrame frame) {
        String string;
        if (isEmpty()) {
            string = "brak studentow";
        } else {
            string = "Lista studentow:\n" + this;
        }

        JOptionPane.showMessageDialog(frame, string);
    }

    public Student wybierz(JFrame frame) {
        if (isEmpty()) {
            return null;
        }

        String menu = "Podaj nr studenta:\n" + this + "\n0 - powrot";
        int n = PobierzDane.pobierzInt(menu, frame);

        if (n == 0) {
            return null;
        }

        try {
            return get(n - 1);
        } catch (java.lang.IndexOutOfBoundsException err) {
            return wybierz(frame);
        }
    }

    public Student usun(JFrame frame) {
        Student student = wybierz(frame);
        remove(student);
        return student;
    }

    public boolean contains(Student student) {
        for (Student student1 : this) {
            if (student1.equals(student)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "brak studentow";
        }

        String string = "";
        for (int i = 0; i < size(); i++) {
            string += String.format("%d) %s\n", i + 1, get(i));
        }

        return string;
    }
}
