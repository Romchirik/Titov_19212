package ru.nsu.titov.model.playfield;

import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.Model;
import ru.nsu.titov.model.ObjectId;

public class Wall extends GameObject {

    WallType type = WallType.VERTICAL;

    public Wall(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        ID = ObjectId.WALL;
    }

    public void setType(WallType type) {
        this.type = type;
    }

    @Override
    public void tick(Model model) {

    }

    @Override
    public void onCollide(GameObject object, Model model) {

    }

    public WallType getType() {
        return type;
    }
}
