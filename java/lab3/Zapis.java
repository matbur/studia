/**
 * Created by matbur on 07.12.15.
 */

public class Zapis {
    private Student student;
    private Grupa grupa;

    public Zapis(Student student, Grupa grupa) {
        this.student = student;
        this.grupa = grupa;
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
