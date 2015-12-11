/**
 * Created by matbur on 10.12.15.
 */

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class Ramka extends JFrame {
    private Ramka() {
        super("MiniEdukacja");

        StudentList studentList = new StudentList();
        GrupaList grupaList = new GrupaList();
        ZapisList zapisList = new ZapisList();

        JPanel mainPanel = new JPanel(new GridLayout());
        setContentPane(mainPanel);

        JPanel studentPanel = new JPanel(new GridLayout(5, 1));
        newLabel(studentPanel, "STUDENCI");
        mainPanel.add(studentPanel);

        JPanel grupaPanel = new JPanel(new GridLayout(5, 1));
        newLabel(grupaPanel, "GRUPY");
        mainPanel.add(grupaPanel);

        JPanel zapisPanel = new JPanel(new GridLayout(5, 1));
        newLabel(zapisPanel, "ZAPISY");
        mainPanel.add(zapisPanel);

        newButton(studentPanel, "dodaj").addActionListener(e1 -> {
            studentList.dodajNowy(this);
            studentList.wyswietl(this);
        });

        newButton(studentPanel, "usun").addActionListener(e -> {
            zapisList.usun(studentList.usun(this));
            studentList.wyswietl(this);
        });

        newButton(studentPanel, "sortuj liste").addActionListener(e -> {
            Collections.sort(studentList, new StudentComparable());
            studentList.wyswietl(this);
        });

        newButton(studentPanel, "pokaz liste").addActionListener(e -> {
            studentList.wyswietl(this);
        });


        newButton(grupaPanel, "dodaj").addActionListener(e -> {
            grupaList.dodajNowy(this);
            grupaList.wyswietl(this);
        });

        newButton(grupaPanel, "usun").addActionListener(e -> {
            zapisList.usun(grupaList.usun(this));
            grupaList.wyswietl(this);
        });

        newButton(grupaPanel, "sortuj liste").addActionListener(e -> {
            Collections.sort(grupaList, new GrupaComparable());
            grupaList.wyswietl(this);
        });

        newButton(grupaPanel, "pokaz liste").addActionListener(e -> {
            grupaList.wyswietl(this);
        });


        newButton(zapisPanel, "dodaj").addActionListener(e -> {
            zapisList.dodajNowy(studentList, grupaList, this);
        });

        newButton(zapisPanel, "usun").addActionListener(e -> {
            zapisList.usun(this);
        });

        newButton(zapisPanel, "pokaz zapisy studentow").addActionListener(e -> {
            zapisList.wyswieltStudentow(studentList, this);
        });

        newButton(zapisPanel, "pokaz zapisy do grup").addActionListener(e -> {
            zapisList.wyswieltGrupy(grupaList, this);
        });


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 300);
        setMinimumSize(new Dimension(400, 200));
        setLocation(300, 200);
        setAlwaysOnTop(true);
        setVisible(true);
    }

    private static JButton newButton(JPanel panel, String string) {
        JButton button = new JButton(string);
        panel.add(button);
        button.setBackground(new Color(253, 238, 215));
        button.setForeground(new Color(154, 52, 45));
        return button;
    }

    private static void newLabel(JPanel panel, String string) {
        JLabel label = new JLabel(string, JLabel.CENTER);
        label.setForeground(new Color(255, 248, 237));
        panel.add(label);
        panel.setBackground(new Color(154, 52, 45));
    }

    public static void main(String[] args) {
        new Ramka();
    }
}
