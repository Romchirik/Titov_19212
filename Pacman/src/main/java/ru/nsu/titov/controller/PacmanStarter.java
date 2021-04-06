package ru.nsu.titov.controller;


import javax.swing.*;
import java.io.IOException;

final public class PacmanStarter {

    public static void main(final String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new MainController();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


}

