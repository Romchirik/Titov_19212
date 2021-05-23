package ru.nsu.titov.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ru.nsu.titov.model.GameObject;


public class EnergizerPainter {
    private static int animationCounter = 0;
    private static int delta = 1;

    private static Image smol = new Image("textures/smol.png");
    private static Image big = new Image("textures/energizer.png");

    public static void drawEnergizer(GameObject object, GraphicsContext canvas) {
        animationCounter++;

        if (animationCounter < 120) {
            canvas.drawImage(smol, object.getX() * UISettings.CELL_SIZE, object.getY() * UISettings.CELL_SIZE);
        } else {
            canvas.drawImage(big, object.getX() * UISettings.CELL_SIZE, object.getY() * UISettings.CELL_SIZE);
        }

        if (animationCounter == 240) {
            animationCounter = 0;
        }
    }
}
