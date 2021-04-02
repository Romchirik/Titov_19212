package ru.nsu.titov.model.ghosts;

import ru.nsu.titov.controller.Settings;
import ru.nsu.titov.model.ModelController;
import ru.nsu.titov.model.ObjectId;

import java.awt.*;

public class Clyde extends Ghost{
    public Clyde(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        ID = ObjectId.CLYDE;
    }
    //допилить тактику
    @Override
    void updateTactic(ModelController model) {

    }

    @Override
    public void tick(ModelController model) {

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, Settings.CELL_SIZE, Settings.CELL_SIZE);
    }
}
