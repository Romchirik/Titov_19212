package ru.nsu.titov.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import ru.nsu.titov.model.GameObject;

public class PacmanPainter {

    private static int animationCounter = 0;
    private static int delta = 1;


    public static void drawPacman(GameObject object, GraphicsContext canvas) {
        int objectVelocity = object.getVelocity();
        int ticksPassed = object.getTicksPassed();

        double x = object.getX() * UISettings.CELL_SIZE;
        double y = object.getY() * UISettings.CELL_SIZE;

        double shift = (UISettings.CELL_SIZE / (double) objectVelocity) * ticksPassed;
        if (animationCounter == 10) {
            delta = -1;
        }
        if (animationCounter == 0) {
            delta = 1;
        }
        animationCounter += delta;

        int startAngle = 0;
        switch (object.getDirection()) {
            case UP -> {
                startAngle = 90;
                y += shift;
            }
            case DOWN -> {
                startAngle = 270;
                y += shift;
            }
            case LEFT -> {
                startAngle = 180;
                x += shift;
            }
            case RIGHT -> {
                startAngle = 0;
                x += shift;
            }
        }

        canvas.setFill(Color.YELLOW);

        canvas.fillArc(x, y,
                UISettings.CELL_SIZE, UISettings.CELL_SIZE,
                startAngle + animationCounter * 6, 360 - animationCounter * 12, ArcType.ROUND);


//        canvas.fillArc(object.getX() * UISettings.CELL_SIZE, object.getY() * UISettings.CELL_SIZE,
//                UISettings.CELL_SIZE, UISettings.CELL_SIZE,
//                startAngle + animationCounter * 6, 360 - animationCounter * 12, ArcType.ROUND);

    }
}
