package ru.nsu.titov.model.playfield;

import ru.nsu.titov.controller.Settings;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.Model;
import ru.nsu.titov.model.ObjectId;

public class Energizer extends GameObject {

    private final int rageDuration = 10 * Settings.FPS;
    final private int COST = 50;

    public Energizer(int x, int y) {
        super(x, y);
        ID = ObjectId.ENERGIZER;
    }

    @Override
    public void tick(Model model) {

    }

    @Override
    public void onCollide(GameObject object, Model model) {
        if (object.getID() == ObjectId.PACMAN) {
            model.setRageMode(rageDuration);
        }
    }


}
