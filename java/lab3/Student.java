/**
 * Created by matbur on 07.12.15.
 */

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

    public static String pokazListeStudentow(List<Student> studentList) {
        String string = "";
        for (int i = 0; i < studentList.size(); i++) {
            string += String.format("%d) %s\n", i + 1, studentList.get(i));
        }

        return string;
    }

    public static Student wybierzStudenta(List<Student> studentList) {
        String menu = "Podaj nr studenta:\n" + pokazListeStudentow(studentList);
        int n = PobierzDane.pobierzInt(menu) - 1;

        if (n < 0 || n >= studentList.size()) {
            return wybierzStudenta(studentList);
        }

        return studentList.get(n);
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

    @Override
    public String toString() {
        return String.format("%s %s, %d", nazwisko, imie, indeks);
    }
}

class StudentComparable implements Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
        return o1.getNazwisko().compareToIgnoreCase(o2.getNazwisko());
    }
}
