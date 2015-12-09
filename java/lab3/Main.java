/**
 * Created by matbur on 07.12.15.
 */

import java.util.Collections;

public class Main {
    private StudentList studentList;
    private GrupaList grupaList;
    private ZapisList zapisList;

    public Main() {
        studentList = new StudentList();
        grupaList = new GrupaList();
        zapisList = new ZapisList();
    }

    public static void main(String[] args) {
        pokazMenu();
    }

    public static void pokazMenu() {
        Main m = new Main();
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
                    m.studentList.dodajNowy();
                    m.studentList.wyswietl();
                    break;
                case 12:
                    m.zapisList.usun(m.studentList.usun());
                    m.studentList.wyswietl();
                    break;
                case 13:
                    Collections.sort(m.studentList, new StudentComparable());
                    m.studentList.wyswietl();
                    break;
                case 14:
                    m.studentList.wyswietl();
                    break;
                case 21:
                    m.grupaList.dodajNowy();
                    m.grupaList.wyswietl();
                    break;
                case 22:
                    m.zapisList.usun(m.grupaList.usun());
                    m.grupaList.wyswietl();
                    break;
                case 23:
                    Collections.sort(m.grupaList, new GrupaComparable());
                    m.grupaList.wyswietl();
                    break;
                case 24:
                    m.grupaList.wyswietl();
                    break;
                case 31:
                    m.zapisList.dodajNowy(m.studentList, m.grupaList);
                    break;
                case 32:
                    m.zapisList.usun();
                    break;
                case 33:
                    m.zapisList.wyswieltStudentow(m.studentList);
                    break;
                case 34:
                    m.zapisList.wyswieltGrupy(m.grupaList);
                    break;
                case 0:
                    System.exit(0);
            }
        }
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
