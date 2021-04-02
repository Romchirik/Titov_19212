package ru.nsu.titov.model;

import ru.nsu.titov.model.map.Map;
import ru.nsu.titov.model.map.MapLoader;
import ru.nsu.titov.model.map.Wall;
import ru.nsu.titov.model.pacman.Pacman;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ModelController {
    Map map;
    ArrayList<GameObject> ghosts;
    Pacman pacman;

    int currentScore = 0;

    public ModelController() throws IOException {
        map = MapLoader.loadMap();
        pacman = new Pacman(2, 2);
        pacman.setVelocity(1);
    }

    public void tick() {
        int pacNextX = pacman.getNextLogicalX();
        int pacNextY = pacman.getNextLogicalY();


    }

    public void paint(Graphics g) {
        map.getMap().forEach(object -> object.paint(g));
//        ghosts.forEach(object -> object.paint(g));

        pacman.paint(g);
    }

    public void setPacmanDirection(Direction direction){
        pacman.setDirection(direction);
    }

}
