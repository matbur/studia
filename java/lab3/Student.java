/**
 * Created by matbur on 07.12.15.
 */

import javax.swing.*;
import java.util.Comparator;
import java.util.List;

public class Student {
    private String imie;
    private String nazwisko;
    private int indeks;

    public Student() {
        imie = "unknown";
        nazwisko = "unknown";
        indeks = 0;
    }

    public Student(int indeks) {
        this();
        this.indeks = indeks;
    }

    public Student(String nazwisko) {
        this();
        this.nazwisko = nazwisko;
    }

    public Student(String nazwisko, int indeks) {
        this();
        this.nazwisko = nazwisko;
        this.indeks = indeks;
    }

    public Student(String nazwisko, String imie, int indeks) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.indeks = indeks;
    }

    public static Student nowyStudent() {
        String nazwisko = PobierzDane.pobierzString("Podaj nazwisko studenta:");
        String imie = PobierzDane.pobierzString("Podaj imie studenta:");
        int indeks = PobierzDane.pobierzInt("Podaj nr indeksu studenta " + nazwisko + " " + imie + ":");

        return new Student(nazwisko, imie, indeks);
    }

    public static String pokazStudentow(List<Student> studentList) {
        if (studentList.isEmpty()) {
            return "Brak studentow";
        }

        String string = "";
        for (int i = 0; i < studentList.size(); i++) {
            string += String.format("%d) %s\n", i + 1, studentList.get(i));
        }

        return string;
    }

    public static void wyswietlStudentow(List<Student> studentList) {
        JOptionPane.showMessageDialog(null,
                "Lista studentow:\n" + Student.pokazStudentow(studentList));
    }

    public static Student wybierzStudenta(List<Student> studentList) {
        if (studentList.isEmpty()) {
            return null;
        }

        String menu = "Podaj nr studenta:\n" + pokazStudentow(studentList) + "\n0 - powrot";
        int n = PobierzDane.pobierzInt(menu);

        if (n == 0) {
            return null;
        }

        try {
            return studentList.get(n - 1);
        } catch (java.lang.IndexOutOfBoundsException err) {
            return wybierzStudenta(studentList);
        }
    }

    public static Student usunStudenta(List<Student> studentList) {
        Student student = wybierzStudenta(studentList);
        studentList.remove(student);
        return student;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getIndeks() {
        return indeks;
    }

    public void setIndeks(int indeks) {
        this.indeks = indeks;
    }

    public boolean equals(Student student) {
        return nazwisko.equals(student.nazwisko) && imie.equals(student.imie) && indeks == student.indeks;
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d)", nazwisko, imie, indeks);
    }
}

class StudentComparable implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getNazwisko().compareToIgnoreCase(o2.getNazwisko());
    }
}
