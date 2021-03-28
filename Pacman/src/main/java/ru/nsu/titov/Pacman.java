package ru.nsu.titov;

import ru.nsu.titov.model.Model;
import ru.nsu.titov.view.Window;

import javax.swing.*;
import java.awt.*;

public final class Pacman{

    boolean running = true;
    private Thread mainThread;
    final Window mainWindow;
    Model gameModel = new Model();

    public Pacman() {
        mainWindow = new Window(800, 600, "Pacman", this);
        SwingUtilities.invokeLater(mainWindow);
    }

    public static void main(final String[] args) {
        new Pacman();
    }

    public synchronized void start() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double nanoSeconds = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / nanoSeconds;
            lastTime = now;
            while (delta >= 1) {
                gameModel.tick();
                delta--;
            }
            mainWindow.update(gameModel.getUpdatedObjects());

            frames++;
            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }

        }
    }
}
