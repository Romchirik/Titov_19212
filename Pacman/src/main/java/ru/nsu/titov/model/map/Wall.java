package ru.nsu.titov.model.map;

import ru.nsu.titov.controller.Settings;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ModelController;
import ru.nsu.titov.model.ObjectId;
import ru.nsu.titov.view.MyPainter;

import java.awt.*;

public class Wall extends GameObject {
    public Wall(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        ID = ObjectId.WALL;
    }

    @Override
    public void tick(ModelController model) {

    }

    @Override
    public void onCollide(GameObject object, ModelController model) {

    }

    @Override
    public void paint(MyPainter painter) {

    }
}
