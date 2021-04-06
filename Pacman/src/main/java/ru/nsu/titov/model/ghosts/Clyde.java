package ru.nsu.titov.model.ghosts;

import ru.nsu.titov.controller.Settings;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ModelController;
import ru.nsu.titov.model.ObjectId;
import ru.nsu.titov.view.MyPainter;

import java.awt.*;

public class Clyde extends Ghost {

    public Clyde(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        ID = ObjectId.CLYDE;
    }

    //TODO допилить тактику
    @Override
    void updateTactic(ModelController model) {

    }

    @Override
    public void tick(ModelController model) {

    }
    @Override
    public void paint(MyPainter painter) {

    }
}
