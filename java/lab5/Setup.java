/**
 * Created by matbur on 12.01.16.
 */

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Setup extends JPanel implements ActionListener, ChangeListener {
    int WIDTH;
    JButton startButton;
    JButton pauseButton;
    JButton restartButton;
    JLabel widthLabel;
    JLabel heightLabel;
    JLabel nLabel;
    JSlider widthSlider;
    JSlider heightSlider;
    JSlider nSlider;
    JTextField widthText;
    JTextField heighText;
    JTextField nText;

    public Setup(int w) {
        WIDTH = w - 20;

        startButton = new JButton("START");
        startButton.addActionListener(this);
        startButton.setPreferredSize(new Dimension(WIDTH, 20));
        add(startButton);

        pauseButton = new JButton(("PAUSE"));
        pauseButton.addActionListener(this);
        pauseButton.setEnabled(false);
        pauseButton.setPreferredSize(new Dimension(WIDTH, 20));
        add(pauseButton);

        restartButton = new JButton(("RESTART"));
        restartButton.addActionListener(this);
        restartButton.setEnabled(false);
        restartButton.setPreferredSize(new Dimension(WIDTH, 20));
        add(restartButton);

        widthLabel = new JLabel("w:");
        widthLabel.setPreferredSize(new Dimension(WIDTH / 3, 20));
        add(widthLabel);

        heightLabel = new JLabel("h:");
        heightLabel.setPreferredSize(new Dimension(WIDTH / 3, 20));
        add(heightLabel);

        nLabel = new JLabel("n:");
        nLabel.setPreferredSize(new Dimension(WIDTH / 3, 20));
        add(nLabel);

        widthSlider = new JSlider(JSlider.VERTICAL, 4, 100, 10);
        widthSlider.addChangeListener(this);
        widthSlider.setPreferredSize(new Dimension(WIDTH / 3, 100));
        add(widthSlider);

        heightSlider = new JSlider(JSlider.VERTICAL, 4, 100, 10);
        heightSlider.addChangeListener(this);
        heightSlider.setPreferredSize(new Dimension(WIDTH / 3, 100));
        add(heightSlider);

        nSlider = new JSlider(JSlider.VERTICAL, 1, 100, 4);
        nSlider.addChangeListener(this);
        nSlider.setPreferredSize(new Dimension(WIDTH / 3, 100));
        add(nSlider);

        widthText = new JTextField("10");
        widthText.setPreferredSize(new Dimension(WIDTH / 3, 20));
        widthText.setEnabled(false);
        add(widthText);

        heighText = new JTextField("10");
        heighText.setPreferredSize(new Dimension(WIDTH / 3, 20));
        heighText.setEnabled(false);
        add(heighText);

        nText = new JTextField("4");
        nText.setPreferredSize(new Dimension(WIDTH / 3, 20));
        nText.setEnabled(false);
        add(nText);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = (JButton) e.getSource();

        if (source == startButton) {
            startButton.setText("CONTINUE");
            startButton.setEnabled(false);
            pauseButton.setEnabled(true);
            restartButton.setEnabled(true);

            if (Main.sun == null) {
                Main.w = widthSlider.getValue();
                Main.h = heightSlider.getValue();
                Main.n = nSlider.getValue();

                Main.frame.canvas.updateWH();
                Main.createAll();
                Main.frame.canvas.isReady = true;

                Main.sun.start();
                Main.snails.forEach(Snail::start);
            } else {
                Main.sun.doWait = false;
                Main.sun.interrupt();

                Main.snails.forEach((snail) -> {
                    snail.doWait = false;
                    snail.interrupt();
                });
            }
        } else if (source == pauseButton) {
            startButton.setEnabled(true);
            pauseButton.setEnabled(false);
            restartButton.setEnabled(true);

            Main.sun.doWait = true;
            Main.snails.forEach((snail) -> snail.doWait = true);
        } else if (source == restartButton) {
            pauseButton.doClick();
            Main.sun = null;
            startButton.doClick();
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object source = (JSlider) e.getSource();

        if (source == widthSlider) {
            widthText.setText("" + widthSlider.getValue());
        } else if (source == heightSlider) {
            heighText.setText("" + heightSlider.getValue());
        } else if (source == nSlider) {
            nText.setText("" + nSlider.getValue());
        }

    }
}
