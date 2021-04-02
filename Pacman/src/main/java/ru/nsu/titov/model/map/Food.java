package ru.nsu.titov.model.map;

import ru.nsu.titov.controller.Settings;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ModelController;
import ru.nsu.titov.model.ObjectId;

import java.awt.*;

public class Food extends GameObject {
    public Food(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        ID = ObjectId.FOOD;
    }

    @Override
    public void tick(ModelController model) {

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.green);
        g.fillOval(x, y, Settings.CELL_SIZE / 2, Settings.CELL_SIZE/ 2);
    }
}
