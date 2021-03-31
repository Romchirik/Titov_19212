package ru.nsu.titov.view;

import ru.nsu.titov.controller.InGameKeyboardHandler;
import ru.nsu.titov.model.ModelController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    Timer timer = new Timer(1000 / 60, this);
    ModelController modelController = new ModelController();

    public GamePanel() {
        modelController.initSession();
        setFocusable(true);
        setBackground(Color.BLACK);
        addKeyListener(new InGameKeyboardHandler(modelController));
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        modelController.getGameObjects().forEach(object -> {
            object.draw(g);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        modelController.tick();
        repaint();

    }
}
