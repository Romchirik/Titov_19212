package ru.nsu.titov.model.map;

import ru.nsu.titov.controller.Settings;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ModelController;
import ru.nsu.titov.model.ObjectId;

import java.awt.*;

public class Void extends GameObject {
    public Void(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        ID = ObjectId.VOID;
    }

    @Override
    public void tick(ModelController model) {

    }

    @Override
    public void paint(Graphics g) {

    }
}
