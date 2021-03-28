package ru.nsu.titov.model;

import ru.nsu.titov.model.entities.Direction;
import ru.nsu.titov.model.entities.GameObject;
import ru.nsu.titov.model.entities.pacman.Pacman;

import java.util.ArrayList;

public class Model {

    private ArrayList<GameObject> scene = new ArrayList<>();
    private ArrayList<GameObject> collided = new ArrayList<>();
    private ArrayList<GameObject> players = new ArrayList<>();
    private ArrayList<GameObject> updatedObjects = new ArrayList<>();
    public boolean tick() {
        updatedObjects.clear();

        moveAll();
        checkCollisions();
        finalizeTick();

        return true;
    }

    void startNewSession() {
        players.add(new Pacman(0, 1, 10, 10, Direction.RIGHT));
        updatedObjects.add(players.get(0));
    }

    private void moveAll() {

    }

    private void checkCollisions() {

    }

    private void finalizeTick() {

    }

    public ArrayList<GameObject> getUpdatedObjects() {
        return updatedObjects;
    }


}
