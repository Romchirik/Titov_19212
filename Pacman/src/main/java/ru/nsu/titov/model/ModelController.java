package ru.nsu.titov.model;

import java.util.ArrayList;

import static ru.nsu.titov.model.GameObject.checkCollision;

public final class ModelController {

    private Pacman pacman;
    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    public void tick() {
        gameObjects.forEach(GameObject::tick);
        gameObjects.forEach(object -> {
            checkCollision(object, pacman);
        });
    }

    public void initSession() {
        pacman = new Pacman(0, 0, 32, 32);
        pacman.setVelocity(
                1
        );
        gameObjects.add(pacman);
    }

    public void finishSession() {
        gameObjects.clear();
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setPacmanDirection(Direction dir) {
        pacman.setDirection(dir);
    }
}
