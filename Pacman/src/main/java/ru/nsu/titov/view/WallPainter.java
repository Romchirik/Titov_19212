package ru.nsu.titov.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ru.nsu.titov.model.playfield.Wall;

public class WallPainter {
    static Image wallSingle = new Image("textures/wall_single.png");

    static Image wallUp = new Image("textures/wall_up.png");
    static Image wallRight = new Image("textures/wall_right.png");
    static Image wallDown = new Image("textures/wall_down.png");
    static Image wallLeft = new Image("textures/wall_left.png");

    static Image wallVertical = new Image("textures/wall_vertical.png");
    static Image walHorizontal = new Image("textures/wall_horizontal.png");

    static Image wallUpRight = new Image("textures/wall_up_right.png");
    static Image wallRightDown = new Image("textures/wall_right_down.png");
    static Image wallDownLeft = new Image("textures/wall_down_left.png");
    static Image wallLeftUp = new Image("textures/wall_left_up.png");

    static Image wallTLeft = new Image("textures/wall_t_left.png");
    static Image wallTRight = new Image("textures/wall_t_right.png");
    static Image wallTUp = new Image("textures/wall_t_up.png");
    static Image wallTDown = new Image("textures/wall_t_down.png");

    static Image wallXShaped = new Image("textures/wall_x_shaped.png");

    public static void drawWall(Wall wall, GraphicsContext canvas) {
        switch (wall.getType()) {
            case VERTICAL -> canvas.drawImage(wallVertical, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
            case HORIZONTAL -> canvas.drawImage(walHorizontal, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
            case CORNER_DOWN_LEFT -> canvas.drawImage(wallDownLeft, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
            case CORNER_LEFT_UP -> canvas.drawImage(wallLeftUp, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
            case CORNER_UP_RIGHT -> canvas.drawImage(wallUpRight, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
            case CORNER_RIGHT_DOWN -> canvas.drawImage(wallRightDown, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
            case RIGHT -> canvas.drawImage(wallRight, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
            case UP -> canvas.drawImage(wallUp, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
            case LEFT -> canvas.drawImage(wallLeft, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
            case DOWN -> canvas.drawImage(wallDown, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
            case X_SHAPED -> canvas.drawImage(wallXShaped, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
            case T_UP -> canvas.drawImage(wallTUp, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
            case T_DOWN -> canvas.drawImage(wallTDown, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
            case T_LEFT -> canvas.drawImage(wallTLeft, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
            case T_RIGHT -> canvas.drawImage(wallTRight, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
            case SINGLE -> canvas.drawImage(wallSingle, wall.getX() * UISettings.CELL_SIZE, wall.getY() * UISettings.CELL_SIZE);
        }
    }


}
