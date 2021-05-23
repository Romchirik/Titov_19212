package ru.nsu.titov.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ru.nsu.titov.model.ghosts.Ghost;
import ru.nsu.titov.model.ghosts.GhostState;


public class GhostPainter {
    private static final Image blinky = new Image("textures/blinky.png");
    private static final Image pinky = new Image("textures/pinky.png");
    private static final Image inky = new Image("textures/inky.png");
    private static final Image clyde = new Image("textures/clyde.png");

    private static final Image frightened = new Image("textures/frightened.png");
    private static final Image eyes = new Image("textures/eyes.png");

    public static void drawObject(Ghost object, GraphicsContext canvas) {

        int objectVelocity = object.getVelocity();
        int ticksPassed = object.getTicksPassed();

        double x = object.getX() * UISettings.CELL_SIZE;
        double y = object.getY() * UISettings.CELL_SIZE;

        double shift = (UISettings.CELL_SIZE / (double) objectVelocity) * ticksPassed;
        switch (object.getDirection()) {
            case UP, DOWN -> {
                y += shift;
            }
            case LEFT, RIGHT -> {
                x += shift;
            }
        }
//        switch (object.getDirection()) {
//            case UP -> y -= shift;
//            case DOWN -> y += shift;
//            case LEFT -> x -= shift;
//            case RIGHT -> x += shift;
//        }

        if (object.getState() == GhostState.FRIGHTENED) {
            canvas.drawImage(frightened, x, y);
        } else if (object.getState() == GhostState.EATEN) {
            canvas.drawImage(eyes, x, y);
        } else {
            switch (object.getID()) {
                case BLINKY -> canvas.drawImage(blinky, x, y);
                case INKY -> canvas.drawImage(inky, x, y);
                case CLYDE -> canvas.drawImage(clyde, x, y);
                case PINKY -> canvas.drawImage(pinky, x, y);
            }
        }
    }

}

