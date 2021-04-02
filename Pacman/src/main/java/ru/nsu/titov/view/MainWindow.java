package ru.nsu.titov.view;

import javax.swing.*;
import java.awt.*;


public final class MainWindow extends JFrame {

    public MainWindow(boolean syncOn) {
        GamePanel a = new GamePanel(syncOn);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            //TODO убрать вывод стектрейса
            e.printStackTrace();
        }

        setSize(new Dimension(1080, 1080));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pacman");
        this.add(a);
        setVisible(true);
    }
}

