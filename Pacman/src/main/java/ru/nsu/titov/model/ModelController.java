package ru.nsu.titov.model;

import java.util.ArrayList;

import static ru.nsu.titov.model.GameObject.checkCollision;

public final class ModelController {

    boolean gameOver = false;
    int pacmanLives;
    private Pacman pacman;
    private GameObject[][] map;

    private ArrayList<GameObject> gameObjects = new ArrayList<>();


    public boolean tick() {
        gameObjects.forEach(object -> object.tick(this));

        gameObjects.forEach(object -> {
            if (checkCollision(object, pacman)) {
                object.onCollide(pacman);
            }
        });
        return true;
    }

    public void initSession() {
        pacman = new Pacman(0, 0, 32, 32);
        pacman.setVelocity(1);
        gameObjects.add(new Wall(200, 200, 32, 32));
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

    GameObject getMapObjectAt(int x, int y){
        return map[y][x];
    }


}
