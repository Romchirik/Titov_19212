package ru.nsu.titov.model.map;

import ru.nsu.titov.controller.Settings;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ModelController;
import ru.nsu.titov.model.ObjectId;
import ru.nsu.titov.view.MyPainter;

import java.awt.*;

public class Food extends GameObject {
    private boolean eaten = false;

    public Food(int logicalX, int logicalY) {

        super(logicalX, logicalY);
        ID = ObjectId.FOOD;
    }

    @Override
    public void tick(ModelController model) {

    }

    @Override
    public void onCollide(GameObject object, ModelController model) {
        if (object.getID() == ObjectId.PACMAN) {
            model.increaseScore(100);
            eaten = true;
        }
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    @Override
    public void paint(MyPainter painter) {

    }
}
