package ru.nsu.titov.model.map;

import ru.nsu.titov.controller.Settings;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ModelController;
import ru.nsu.titov.model.ObjectId;

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
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, Settings.CELL_SIZE, Settings.CELL_SIZE);
    }
}
