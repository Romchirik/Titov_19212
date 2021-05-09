package ru.nsu.titov.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ru.nsu.titov.model.GameObject;


public class MovingObjectsPainter {
    public static void drawObject(GameObject object, GraphicsContext canvas, Image texture) {
        int objectVelocity = object.getVelocity();
        int ticksPassed = object.getTicksPassed();

        double x = object.getX() * UISettings.CELL_SIZE;
        double y = object.getY() * UISettings.CELL_SIZE;

        double shift = (UISettings.CELL_SIZE / (double) objectVelocity) * ticksPassed;

        switch (object.getDirection()) {
            case UP -> y -= shift;
            case DOWN -> y += shift;
            case LEFT -> x -= shift;
            case RIGHT -> x += shift;
        }
        canvas.drawImage(texture, x, y);
    }

}

