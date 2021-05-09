package ru.nsu.titov.model.playfield;

import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.Model;
import ru.nsu.titov.model.ObjectId;

public class Energizer extends GameObject {


    public Energizer(int x, int y) {
        super(x, y);
        ID = ObjectId.ENERGIZER;
    }

    @Override
    public void tick(Model model) {

    }

    @Override
    public void onCollide(GameObject object, Model model) {

    }


}
