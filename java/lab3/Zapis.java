/**
 * Created by matbur on 07.12.15.
 */

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Zapis {
    private Student student;
    private Grupa grupa;

    public Zapis() {
        this.student = new Student();
        this.grupa = new Grupa();
    }

    public Zapis(Student student, Grupa grupa) {
        this.student = student;
        this.grupa = grupa;
    }

    public static Zapis nowyZapis(List<Student> studentList, List<Grupa> grupaList, List<Zapis> zapisList) {
        if (studentList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nie dodano studentow");
            return null;
        } else if (grupaList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nie dodano grup");
            return null;
        }

        Student student = Student.wybierzStudenta(studentList);
        if (student == null) {
            return null;
        }

        Grupa grupa = Grupa.wybierzGrupe(grupaList);
        if (grupa == null) {
            return null;
        }

        Zapis zapis = new Zapis(student, grupa);
        for (Zapis zapis1 : zapisList) {
            if (zapis1.equals(zapis)) {
                JOptionPane.showMessageDialog(null, "Taki zapis juz istnieje");
                return null;
            }
        }
        return zapis;
    }

    public static String pokazZapisy(List<Zapis> zapisList) {
        if (zapisList.size() == 0) {
            return "Brak zapisow";
        }

        String string = "";
        for (int i = 0; i < zapisList.size(); i++) {
            string += String.format("%d) %s\n", i + 1, zapisList.get(i));
        }
        return string;
    }

    public static void wyswieltZapisy(List<Zapis> zapisList) {
        JOptionPane.showMessageDialog(null,
                Zapis.pokazZapisy(zapisList));
    }

    public static String pokazZapisyStudentow(List<Student> studentList, List<Zapis> zapisList) {
        if (studentList.size() == 0) {
            return "Brak studentow";
        } else if (zapisList.size() == 0) {
            return "Brak zapisow";
        }

        String string = "Lista zapisow wg. studentow:\n\n";
        for (Student student : studentList) {
            string += student + ":\n";
            for (Zapis zapis : zapisList) {
                if (student.equals(zapis.student)) {
                    string += "    - " + zapis.grupa + "\n";
                }
            }
            string += "\n";
        }
        return string;
    }

    public static void wyswieltZapisyStudentow(List<Student> studentList, List<Zapis> zapisList) {
        JOptionPane.showMessageDialog(null,
                Zapis.pokazZapisyStudentow(studentList, zapisList));
    }

    public static String pokazZapisyGrup(List<Grupa> grupaList, List<Zapis> zapisList) {
        if (grupaList.size() == 0) {
            return "Brak grup";
        } else if (zapisList.size() == 0) {
            return "Brak zapisow";
        }

        String string = "Lista zapisow wg. grup:\n\n";
        for (Grupa grupa : grupaList) {
            string += grupa + ":\n";
            for (Zapis zapis : zapisList) {
                if (grupa.equals(zapis.grupa)) {
                    string += "    - " + zapis.student + "\n";
                }
            }
            string += "\n";
        }
        return string;
    }

    public static void wyswieltZapisyGrup(List<Grupa> grupaList, List<Zapis> zapisList) {
        JOptionPane.showMessageDialog(null,
                Zapis.pokazZapisyGrup(grupaList, zapisList));
    }

    public static Zapis wybierzZapis(List<Zapis> zapisList) {
        if (zapisList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Brak zapisow");
            return null;
        }

        String menu = "Podaj nr grupy:\n" + pokazZapisy(zapisList) + "\n0 - powrot";
        int n = PobierzDane.pobierzInt(menu);

        if (n == 0) {
            System.out.println("null");
            return null;
        }

        try {
            return zapisList.get(n - 1);
        } catch (Exception e) {
            return wybierzZapis(zapisList);
        }
    }

    public static void usunZapis(List<Zapis> zapisList) {
        Zapis zapis = wybierzZapis(zapisList);
        if (zapis == null) {
            return;
        }
        zapisList.remove(zapis);
    }

    public static void usunZapis(List<Zapis> zapisList, Student student) {
        List<Zapis> toRemoveZapisList = new ArrayList<>();

        for (Zapis zapis : zapisList) {
            if (zapis.student.equals(student))
                toRemoveZapisList.add(zapis);
        }
        zapisList.removeAll(toRemoveZapisList);
    }

    public static void usunZapis(List<Zapis> zapisList, Grupa grupa) {
        List<Zapis> toRemoveZapisList = new ArrayList<>();

        for (Zapis zapis : zapisList) {
            if (zapis.grupa.equals(grupa))
                toRemoveZapisList.add(zapis);
        }
        zapisList.removeAll(toRemoveZapisList);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Grupa getGrupa() {
        return grupa;
    }

    public void setGrupa(Grupa grupa) {
        this.grupa = grupa;
    }

    public boolean equals(Zapis zapis) {
        return student.equals(zapis.student) && grupa.equals(zapis.grupa);
    }

    @Override
    public String toString() {
        return student + " <-> " + grupa;
    }
}
