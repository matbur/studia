/**
 * Created by matbur on 09.12.15.
 */

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class StudentList extends ArrayList<Student> {
    public void dodajNowy() {
        String nazwisko = PobierzDane.pobierzString("Podaj nazwisko studenta:\n0 - powrot");
        if (nazwisko.equals("0")) {
            return;
        }

        String imie = PobierzDane.pobierzString("Podaj imie studenta:\n0 - powrot");
        if (imie.equals("0")) {
            return;
        }

        int indeks = PobierzDane.pobierzInt("Podaj nr indeksu studenta:\n0 - powrot");
        if (indeks == 0) {
            return;
        }

        Student student = new Student(nazwisko, imie, indeks);
        if (contains(student)) {
            JOptionPane.showMessageDialog(null, "Taki student juz istnieje");
            return;
        }

        add(student);
    }

    public void wyswietl() {
        JOptionPane.showMessageDialog(null, "Lista studentow:\n" + this);
    }

    public Student wybierz() {
        if (isEmpty()) {
            return null;
        }

        String menu = "Podaj nr studenta:\n" + this + "\n0 - powrot";
        int n = PobierzDane.pobierzInt(menu);

        if (n == 0) {
            return null;
        }

        try {
            return get(n - 1);
        } catch (java.lang.IndexOutOfBoundsException err) {
            return wybierz();
        }
    }

    public Student usun() {
        Student student = wybierz();
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
