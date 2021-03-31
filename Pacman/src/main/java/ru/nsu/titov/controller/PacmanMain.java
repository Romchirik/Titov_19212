package ru.nsu.titov.controller;

import ru.nsu.titov.view.MainWindow;

import javax.swing.*;
import java.awt.*;

public final class PacmanMain {
    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            final MainWindow mainWindow = new MainWindow();
            mainWindow.setVisible(true);
        });

    }
}
