/**
 * Created by matbur on 07.12.15.
 */

import java.util.Comparator;

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
