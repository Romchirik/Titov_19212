package ru.nsu.titov.view;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ru.nsu.titov.model.GameObject;
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
        switch (object.getID()) {
            case PACMAN -> PacmanPainter.drawPacman(object, canvas);
            case WALL -> WallPainter.drawWall((Wall) object, canvas);
            case FOOD -> canvas.drawImage(food, object.getX() * 16, object.getY() * 16);
            case BLINKY -> MovingObjectsPainter.drawObject(object, canvas, blinky);
            case PINKY -> MovingObjectsPainter.drawObject(object, canvas, pinky);
            case INKY -> MovingObjectsPainter.drawObject(object, canvas, inky);
            case CLYDE -> MovingObjectsPainter.drawObject(object, canvas, clyde);
        }

    }

}
