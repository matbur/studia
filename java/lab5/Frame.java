/**
 * Created by matbur on 11.01.16.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame extends JFrame implements ActionListener {
    Canvas canvas;
    JButton startButton;

    public Frame(int w, int h) {
        super("ramka");

        canvas = new Canvas(w, h);
        canvas.setPreferredSize(new Dimension(600, 600));
        add(canvas);

        startButton = new JButton("START");
        startButton.addActionListener(this);
        add(startButton);

        setSize(700, 650);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == startButton) {
            if (Main.leaf.isAlive()) {
                JOptionPane.showMessageDialog(this, "dziala");
            } else {
                Main.leaf.start();
                Main.snails.forEach(Snail::start);
            }
        }
    }
}
