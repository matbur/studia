/**
 * Created by matbur on 07.12.15.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Main {
    List<Student> studentList;
    List<Grupa> grupaList;
    List<Zapis> zapisList;

    public Main() {
        studentList = new ArrayList<>();
        grupaList = new ArrayList<>();
        zapisList = new LinkedList<>();
    }

    public static void main(String[] args) {
        pokazMenu(new Main());
    }

    public static void pokazMenu(Main m) {
        String menu = "M E N U\n" +
                "11 - dodaj nowego studenta\n" +
                "12 - usun studenta\n" +
                "13 - sortuj liste studentow\n" +
                "14 - pokaz liste studentow\n\n" +
                "21 - dodaj nowa grupe\n" +
                "22 - usun grupe\n" +
                "23 - sortuj liste grup\n" +
                "24 - pokaz liste grup\n\n" +
                "31 - dodaj nowy zapis\n" +
                "32 - usun zapis\n" +
                "33 - pokaz liste zapisow wg. studentow\n" +
                "34 - pokaz liste zapisow wg. grup\n\n" +
                "0 - zakoncz program";

        while (true) {
            switch (PobierzDane.pobierzInt(menu)) {
                case 11:
                    m.studentList.add(Student.nowyStudent());
                    Student.wyswietlStudentow(m.studentList);
                    break;
                case 12:
                    Zapis.usunZapis(m.zapisList, Student.usunStudenta(m.studentList));
                    Student.wyswietlStudentow(m.studentList);
                    break;
                case 13:
                    Collections.sort(m.studentList, new StudentComparable());
                    Student.wyswietlStudentow(m.studentList);
                    break;
                case 14:
                    Student.wyswietlStudentow(m.studentList);
                    break;
                case 21:
                    m.grupaList.add(Grupa.nowaGrupa());
                    Grupa.wyswietlGrupy(m.grupaList);
                    break;
                case 22:
                    Zapis.usunZapis(m.zapisList, Grupa.usunGrupe(m.grupaList));
                    Grupa.wyswietlGrupy(m.grupaList);
                    break;
                case 23:
                    Collections.sort(m.grupaList, new GrupaComparable());
                    Grupa.wyswietlGrupy(m.grupaList);
                    break;
                case 24:
                    Grupa.wyswietlGrupy(m.grupaList);
                    break;
                case 31:
                    Zapis zapis = Zapis.nowyZapis(m.studentList, m.grupaList, m.zapisList);
                    if (zapis != null) {
                        m.zapisList.add(zapis);
                    }
                    break;
                case 32:
                    Zapis.usunZapis(m.zapisList);
                    break;
                case 33:
                    Zapis.wyswieltZapisyStudentow(m.studentList, m.zapisList);
                    break;
                case 34:
                    Zapis.wyswieltZapisyGrup(m.grupaList, m.zapisList);
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    public static void test() {
        Main m = new Main();
        m.studentList.add(new Student(1));
        m.studentList.add(new Student(2));
        m.studentList.add(new Student(3));
        m.studentList.add(new Student(4));

        m.grupaList.add(new Grupa("gr1"));
        m.grupaList.add(new Grupa("gr2"));
        m.grupaList.add(new Grupa("gr3"));

        System.out.println(m);

        m.zapisList.add(new Zapis(m.studentList.get(0), m.grupaList.get(0)));
        m.zapisList.add(new Zapis(m.studentList.get(1), m.grupaList.get(0)));
        m.zapisList.add(new Zapis(m.studentList.get(0), m.grupaList.get(1)));

        System.out.println(m);
        Zapis.usunZapis(m.zapisList);
        System.out.println(m);
    }

    @Override
    public String toString() {
        return "Main{" +
                "\n\tstudentList=" + studentList +
                "\n\t grupaList=" + grupaList +
                ",\n\t zapisList=" + zapisList +
                "\n}";
    }
}
