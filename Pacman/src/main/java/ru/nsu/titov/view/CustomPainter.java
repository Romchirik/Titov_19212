package ru.nsu.titov.view;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ghosts.Ghost;
import ru.nsu.titov.model.playfield.Wall;

public class CustomPainter {

    Image blinky = new Image("textures/blinky.png");
    Image pinky = new Image("textures/pinky.png");
    Image inky = new Image("textures/inky.png");
    Image clyde = new Image("textures/clyde.png");

    Image food = new Image("textures/food.png");

    public CustomPainter() {

    }

    public void paint(GameObject object, GraphicsContext canvas) {
        //сделать Painter и наследовать кажды отрисовщик от него
        switch (object.getID()) {
            case PACMAN -> PacmanPainter.drawPacman(object, canvas);
            case WALL -> WallPainter.drawWall((Wall) object, canvas);
            case FOOD -> canvas.drawImage(food, object.getX() * UISettings.CELL_SIZE, object.getY() * UISettings.CELL_SIZE);
            case BLINKY, INKY, PINKY, CLYDE -> GhostPainter.drawObject((Ghost) object, canvas);
            case ENERGIZER -> EnergizerPainter.drawEnergizer(object, canvas);
        }

    }
}
