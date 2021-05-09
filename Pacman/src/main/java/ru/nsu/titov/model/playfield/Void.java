package ru.nsu.titov.model.playfield;

import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.Model;
import ru.nsu.titov.model.ObjectId;

public class Void extends GameObject {
    public Void(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        ID = ObjectId.VOID;
    }

    @Override
    public void tick(Model model) {

    }


    @Override
    public void onCollide(GameObject object, Model model) {

    }

}
