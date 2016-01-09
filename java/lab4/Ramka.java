/**
 * Created by matbur on 10.12.15.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ramka extends JFrame implements ActionListener {
    JButton dodajGrupe,
            dodajStudenta,
            dodajZapis,
            pokazListeGrup,
            pokazListeStudentow,
            pokazZapisyGrup,
            pokazZapisyStudentow,
            sortujListeGrup,
            sortujListeStudentow,
            usunGrupe,
            usunStudenta,
            usunZapis;

    JLabel studentLabel,
            grupaLabel,
            zapisLabel;

    JPanel mainPanel,
            studentPanel,
            grupaPanel,
            zapisPanel;

    GrupaList grupaList;
    StudentList studentList;
    ZapisList zapisList;


    private Ramka() {
        super("MiniEdukacja");

        dodajGrupe = new JButton("dodaj");
        dodajStudenta = new JButton("dodaj");
        dodajZapis = new JButton("dodaj");
        pokazListeGrup = new JButton("pokaz liste");
        pokazListeStudentow = new JButton("pokaz liste");
        pokazZapisyGrup = new JButton("pokaz zapisy do grup");
        pokazZapisyStudentow = new JButton("pokaz zapisy studentow");
        sortujListeGrup = new JButton("sortuj liste");
        sortujListeStudentow = new JButton("sortuj liste");
        usunGrupe = new JButton("usun");
        usunStudenta = new JButton("usun");
        usunZapis = new JButton("usun");

        studentLabel = new JLabel("STUDENCI", JLabel.CENTER);
        grupaLabel = new JLabel("GRUPY", JLabel.CENTER);
        zapisLabel = new JLabel("ZAPISY", JLabel.CENTER);

        mainPanel = new JPanel(new GridLayout());
        studentPanel = new JPanel(new GridLayout(5, 1));
        grupaPanel = new JPanel(new GridLayout(5, 1));
        zapisPanel = new JPanel(new GridLayout(5, 1));

        grupaList = new GrupaList();
        studentList = new StudentList();
        zapisList = new ZapisList();

        setContentPane(mainPanel);
        mainPanel.add(studentPanel);
        mainPanel.add(grupaPanel);
        mainPanel.add(zapisPanel);

        studentPanel.add(studentLabel);
        studentPanel.add(dodajStudenta);
        studentPanel.add(usunStudenta);
        studentPanel.add(sortujListeStudentow);
        studentPanel.add(pokazListeStudentow);

        grupaPanel.add(grupaLabel);
        grupaPanel.add(dodajGrupe);
        grupaPanel.add(usunGrupe);
        grupaPanel.add(sortujListeGrup);
        grupaPanel.add(pokazListeGrup);

        zapisPanel.add(zapisLabel);
        zapisPanel.add(dodajZapis);
        zapisPanel.add(usunZapis);
        zapisPanel.add(pokazZapisyStudentow);
        zapisPanel.add(pokazZapisyGrup);

        List<JButton> buttons = new ArrayList<>();

        buttons.add(dodajStudenta);
        buttons.add(dodajGrupe);
        buttons.add(dodajZapis);
        buttons.add(usunStudenta);
        buttons.add(usunGrupe);
        buttons.add(usunZapis);
        buttons.add(sortujListeStudentow);
        buttons.add(sortujListeGrup);
        buttons.add(pokazListeStudentow);
        buttons.add(pokazListeGrup);
        buttons.add(pokazZapisyStudentow);
        buttons.add(pokazZapisyGrup);

        for (JButton button : buttons) {
            button.addActionListener(this);
            button.setBackground(new Color(253, 238, 215));
            button.setForeground(new Color(154, 52, 45));
        }

        studentLabel.setForeground(new Color(255, 248, 237));
        grupaLabel.setForeground(new Color(255, 248, 237));
        zapisLabel.setForeground(new Color(255, 248, 237));

        studentPanel.setBackground(new Color(154, 52, 45));
        grupaPanel.setBackground(new Color(154, 52, 45));
        zapisPanel.setBackground(new Color(154, 52, 45));


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(700, 300);
        setMinimumSize(new Dimension(400, 200));
        setLocation(300, 200);
//        setAlwaysOnTop(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object object = e.getSource();

        if (object == dodajStudenta) {
            studentList.dodajNowy(this);
            studentList.wyswietl(this);

        } else if (object == dodajGrupe) {
            grupaList.dodajNowy(this);
            grupaList.wyswietl(this);

        } else if (object == dodajZapis) {
            zapisList.dodajNowy(studentList, grupaList, this);

        } else if (object == usunStudenta) {
            zapisList.usun(studentList.usun(this));
            studentList.wyswietl(this);

        } else if (object == usunGrupe) {
            zapisList.usun(grupaList.usun(this));
            grupaList.wyswietl(this);

        } else if (object == usunZapis) {
            zapisList.usun(this);

        } else if (object == sortujListeStudentow) {
            Collections.sort(studentList, new StudentComparable());
            studentList.wyswietl(this);

        } else if (object == sortujListeGrup) {
            Collections.sort(grupaList, new GrupaComparable());
            grupaList.wyswietl(this);

        } else if (object == pokazListeStudentow) {
            studentList.wyswietl(this);

        } else if (object == pokazListeGrup) {
            grupaList.wyswietl(this);

        } else if (object == pokazZapisyStudentow) {
            zapisList.wyswieltStudentow(studentList, this);

        } else if (object == pokazZapisyGrup) {
            zapisList.wyswieltGrupy(grupaList, this);
        }
    }

    private void nowyStudent() {
        JFrame frame = new JFrame("student");
//        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(450, 150);
        frame.setLayout(new FlowLayout());

        JLabel imieLabel,
                nazwiskoLabel,
                indeksLabel;

        imieLabel = new JLabel("imie:", SwingConstants.RIGHT);
        nazwiskoLabel = new JLabel("nazwisko:", SwingConstants.RIGHT);
        indeksLabel = new JLabel("indeks:", SwingConstants.RIGHT);

        JTextField imieField,
                nazwiskoField,
                indeksField;

        imieField = new JTextField(20);
        nazwiskoField = new JTextField(20);
        indeksField = new JTextField(20);

        JButton zapisButton,
                anulujButton;

        zapisButton = new JButton("dodaj");
        anulujButton = new JButton("anuluj");

        JPanel mainPanel,
                imiePanel,
                nazwiskoPanel,
                indeksPanel,
                buttonPanel;

        mainPanel = new JPanel(new GridLayout(4, 1));
        imiePanel = new JPanel(new FlowLayout());
        nazwiskoPanel = new JPanel(new FlowLayout());
        indeksPanel = new JPanel(new FlowLayout());
        buttonPanel = new JPanel(new FlowLayout());

        imiePanel.add(imieLabel);
        imiePanel.add(imieField);
        nazwiskoPanel.add(nazwiskoLabel);
        nazwiskoPanel.add(nazwiskoField);
        indeksPanel.add(indeksLabel);
        indeksPanel.add(indeksField);
        buttonPanel.add(zapisButton);
        buttonPanel.add(anulujButton);

        mainPanel.add(imiePanel);
        mainPanel.add(nazwiskoPanel);
        mainPanel.add(indeksPanel);
        mainPanel.add(buttonPanel);

        zapisButton.addActionListener(e -> {
            String imie = imieField.getText();
            String nazwisko = nazwiskoField.getText();
            int indeks = PobierzDane.pobierzInt(indeksField.getText());
            if (indeks == 0) {
                JOptionPane.showMessageDialog(this, "Indeks musi byc intem");
            } else {
                studentList.add(new Student(nazwisko, imie, indeks));
            }
        });


        frame.setContentPane(mainPanel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Ramka().nowyStudent();
//        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//        System.out.println(dimension.getWidth());
    }
}
