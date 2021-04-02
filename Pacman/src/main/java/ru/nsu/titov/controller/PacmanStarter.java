package ru.nsu.titov.controller;

import ru.nsu.titov.view.MainWindow;

import javax.swing.*;

final public class PacmanStarter {
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow hui = new MainWindow(System.getProperty("os.name").equals("Linux"));
            hui.setVisible(true);
        });
    }
}

