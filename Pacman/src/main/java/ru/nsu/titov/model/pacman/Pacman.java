package ru.nsu.titov.model.pacman;

import ru.nsu.titov.controller.Settings;
import ru.nsu.titov.model.Direction;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ModelController;
import ru.nsu.titov.model.ObjectId;
import ru.nsu.titov.view.MyPainter;

import java.awt.*;

public class Pacman extends GameObject {
    public Pacman(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        ID = ObjectId.PACMAN;
        direction = Direction.RIGHT;
    }


    @Override
    public void tick(ModelController model) {
        switch (direction) {
            case DOWN -> y += velocity;
            case UP -> y -= velocity;
            case LEFT -> x -= velocity;
            case RIGHT -> x += velocity;
        }
    }

    public void tickBack(){
        switch (direction) {
            case DOWN -> y -= velocity;
            case UP -> y += velocity;
            case LEFT -> x += velocity;
            case RIGHT -> x -= velocity;
        }
    }

    @Override
    public void onCollide(GameObject object, ModelController model) {

    }

    @Override
    public void paint(MyPainter painter) {

    }
}
