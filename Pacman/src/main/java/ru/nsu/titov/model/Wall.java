package ru.nsu.titov.model;

import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ModelController;

public class Wall extends GameObject {
    public Wall(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    void tick(ModelController model) {

    }

    @Override
    void onCollide(GameObject obj) {
        switch (obj.getID()){
            case PACMAN -> obj.velocity = 0;
        }
    }


}
