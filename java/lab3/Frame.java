/**
 * Created by matbur on 10.12.15.
 */

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class Frame extends JFrame {
    public Frame() {
        super("MiniEdukacja");

        StudentList studentList = new StudentList();
        GrupaList grupaList = new GrupaList();
        ZapisList zapisList = new ZapisList();

        JPanel mainPanel = new JPanel(new GridLayout());
        setContentPane(mainPanel);

        JPanel studentPane = new JPanel(new GridLayout(5, 1));
        studentPane.add(new JLabel("Studenci", JLabel.CENTER));
        mainPanel.add(studentPane);

        JPanel grupaPane = new JPanel(new GridLayout(5, 1));
        grupaPane.add(new JLabel("Grupy", JLabel.CENTER));
        mainPanel.add(grupaPane);

        JPanel zapisPane = new JPanel(new GridLayout(5, 1));
        zapisPane.add(new JLabel("Zapisy", JLabel.CENTER));
        mainPanel.add(zapisPane);


        newButton(studentPane, "dodaj").addActionListener(e1 -> {
            studentList.dodajNowy(this);
            studentList.wyswietl(this);
        });

        newButton(studentPane, "usun").addActionListener(e -> {
            zapisList.usun(studentList.usun(this));
            studentList.wyswietl(this);
        });

        newButton(studentPane, "sortuj liste").addActionListener(e -> {
            Collections.sort(studentList, new StudentComparable());
            studentList.wyswietl(this);
        });

        newButton(studentPane, "pokaz liste").addActionListener(e -> {
            studentList.wyswietl(this);
        });


        newButton(grupaPane, "dodaj").addActionListener(e -> {
            grupaList.dodajNowy(this);
            grupaList.wyswietl(this);
        });

        newButton(grupaPane, "usun").addActionListener(e -> {
            zapisList.usun(grupaList.usun(this));
            grupaList.wyswietl(this);
        });

        newButton(grupaPane, "sortuj liste").addActionListener(e -> {
            Collections.sort(grupaList, new GrupaComparable());
            grupaList.wyswietl(this);
        });

        newButton(grupaPane, "pokaz liste").addActionListener(e -> {
            grupaList.wyswietl(this);
        });


        newButton(zapisPane, "dodaj").addActionListener(e -> {
            zapisList.dodajNowy(studentList, grupaList, this);
        });

        newButton(zapisPane, "usun").addActionListener(e -> {
            zapisList.usun(this);
        });

        newButton(zapisPane, "pokaz liste wg. studentow").addActionListener(e -> {
            zapisList.wyswieltStudentow(studentList, this);
        });

        newButton(zapisPane, "pokaz liste wg. grup").addActionListener(e -> {
            zapisList.wyswieltGrupy(grupaList, this);
        });


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocation(500, 200);
        setVisible(true);
    }

    public static JButton newButton(JPanel panel, String text) {
        JButton button = new JButton(text);
        panel.add(button);
        return button;
    }

    public static void main(String[] args) {
        new Frame();
    }
}
