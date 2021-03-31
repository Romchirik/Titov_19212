package ru.nsu.titov.view;

import ru.nsu.titov.model.ModelController;

import javax.swing.*;
import java.awt.*;


public final class MainWindow extends JFrame{

    GamePanel a = new GamePanel();
    public MainWindow() {
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Pacman");
        setResizable(false);
        this.add(a);
    }
}

