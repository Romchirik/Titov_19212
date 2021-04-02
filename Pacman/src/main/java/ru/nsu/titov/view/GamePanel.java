package ru.nsu.titov.view;

import ru.nsu.titov.controller.InGameKeyboardListener;
import ru.nsu.titov.model.ModelController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GamePanel extends JPanel implements ActionListener {
    Timer timer = new Timer(1000 / 30, this);
    ModelController model;
    boolean syncOn;

    public GamePanel(boolean syncOn) {

        try {
            model = new ModelController();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to load map",
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        setFocusable(true);
        setBackground(Color.BLACK);
        addKeyListener(new InGameKeyboardListener(model));
        timer.start();
        this.syncOn = syncOn;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        model.paint(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.tick();
        repaint();
        if (syncOn) Toolkit.getDefaultToolkit().sync();
    }
}
