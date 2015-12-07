/**
 * Created by matbur on 07.12.15.
 */

import javax.swing.*;
import java.util.*;

public class Main {
    List<Student> studentList;
    List<Grupa> grupaList;
    List<Zapis> zapisList;

    public Main() {
        studentList = new ArrayList<>();
        grupaList = new ArrayList<>();
        zapisList = new ArrayList<>();
    }

    public static void main(String[] args) {
        pokazMenu(new Main());
    }

    public static void pokazMenu(Main m) {
        String menu = "M E N U\n" +
                "1 - dodaj nowego studenta\n" +
                "2 - pokaz liste studentow\n" +
                "3 - sortuj liste studentow\n\n" +
                "4 - dodaj nowa grupe\n" +
                "5 - pokaz liste grup\n" +
                "6 - sortuj liste grup\n\n" +
                "7 - dodaj nowy zapis\n" +
                "8 - pokaz liste zapisow wg. studentow\n" +
                "9 - pokaz liste zapisow wg. grup\n\n" +
                "0 - zakoncz program";

        while (true) {
            switch (PobierzDane.pobierzInt(menu)) {
                case 1:
                    m.studentList.add(Student.nowyStudent());
                    break;
                case 3:
                    Collections.sort(m.studentList, new StudentComparable());
                case 2:
                    JOptionPane.showMessageDialog(null,
                            "Lista studentow:\n" + Student.pokazListeStudentow(m.studentList));
                    break;
                case 4:
                    m.grupaList.add(Grupa.nowaGrupa());
                    break;
                case 6:
                    Collections.sort(m.grupaList, new GrupaComparable());
                case 5:
                    JOptionPane.showMessageDialog(null,
                            "Lista grup:\n" + Grupa.pokazListeGrup(m.grupaList));
                    break;
                case 7:
                    Zapis zapis = Zapis.nowyZapis(m.studentList, m.grupaList);
                    if (zapis != null) {
                        m.zapisList.add(zapis);
                    }
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null,
                            Zapis.pokazListeZapisowStudentow(m.studentList, m.zapisList));
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null,
                            Zapis.pokazListeZapisowGrup(m.grupaList, m.zapisList));
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}
