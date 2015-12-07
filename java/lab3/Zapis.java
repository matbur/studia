/**
 * Created by matbur on 07.12.15.
 */

import javax.swing.JOptionPane;
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

    public static Zapis nowyZapis(List<Student> studentList, List<Grupa> grupaList) {
        if (studentList.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nie dodano studentow");
            return null;
        } else if (grupaList.size() == 0) {
            JOptionPane.showMessageDialog(null, "Nie dodano grup");
            return null;
        }

        return new Zapis(
                Student.wybierzStudenta(studentList),
                Grupa.wybierzGrupe(grupaList)
        );
    }

    public static String pokazListeZapisowStudentow(List<Student> studentList, List<Zapis> zapisList) {
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

    public static String pokazListeZapisowGrup(List<Grupa> grupaList, List<Zapis> zapisList) {
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
}
