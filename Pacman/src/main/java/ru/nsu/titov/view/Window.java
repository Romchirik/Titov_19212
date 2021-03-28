package ru.nsu.titov.view;

import ru.nsu.titov.Pacman;
import ru.nsu.titov.model.entities.GameObject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Window extends JFrame implements Runnable {
    JPanel drawArea;

    public Window(int width, int height, String title, Pacman game) {
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        drawArea = new JPanel();
        super.add(drawArea);

        game.start();
    }

    @Override
    public void run() {

    }

    public void setMode(){

    }

    public void update(ArrayList<GameObject> updatedObjects) {
        updatedObjects.forEach(gameObject -> {
            gameObject.render(this.getBufferStrategy().getDrawGraphics());
        });
    }
}
