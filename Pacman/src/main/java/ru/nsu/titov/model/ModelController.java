package ru.nsu.titov.model;

import ru.nsu.titov.model.ghosts.Clyde;
import ru.nsu.titov.model.map.Map;
import ru.nsu.titov.model.map.MapLoader;
import ru.nsu.titov.model.pacman.Pacman;
import ru.nsu.titov.view.MyPainter;

import java.io.IOException;
import java.util.ArrayList;

public class ModelController {

    private boolean pacmanEaten = false;
    private Map map;
    private ArrayList<GameObject> ghosts = new ArrayList<>();
    private Pacman pacman;

    int foodsLeft = 0;
    int currentScore = 0;

    public ModelController() throws IOException {
        map = MapLoader.loadMap();
        pacman = new Pacman(2, 2);
        pacman.setVelocity(1);
        ghosts.add(new Clyde(2, 3));
        foodsLeft = map.getFoodsLeft();
    }

    public void tick() {
        if (pacmanEaten) {
            System.exit(0);
        }
        if (map.getObjectAt(pacman.getNextX(), pacman.getNextY()).getID() != ObjectId.WALL) {
            pacman.tick(this);
        }
        map.getObjectAt(pacman.getX(), pacman.getY()).onCollide(pacman, this);
        map.getFood().forEach((food) -> {
            if (GameObject.checkCollision(food, pacman)) {
                food.onCollide(pacman, this);
                System.out.println(foodsLeft);
            }
        });


        ghosts.forEach(ghost -> ghost.tick(this));
        ghosts.forEach(ghost -> {
            if (GameObject.checkCollision(ghost, pacman)) {
                ghost.onCollide(pacman, this);
            }
        });

    }


    public void setPacmanDirection(Direction direction) {
        pacman.setDirection(direction);
    }

    public void increaseScore(int delta) {
        currentScore += delta;
    }

    public void setPacmanEaten(boolean eaten) {
        pacmanEaten = eaten;
    }

    //TODO допилить ресет
    public void reset() {

    }

    public void repaint(MyPainter painter) {
        map.getMap().forEach(painter::paint);
        map.getFood().forEach(painter::paint);
//        ghosts.forEach(painter::paint);

        painter.paint(pacman);
    }
}
