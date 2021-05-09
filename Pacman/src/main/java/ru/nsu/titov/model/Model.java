package ru.nsu.titov.model;


import org.apache.log4j.Logger;
import ru.nsu.titov.controller.GameSession;
import ru.nsu.titov.model.ghosts.Blinky;
import ru.nsu.titov.model.ghosts.Clyde;
import ru.nsu.titov.model.ghosts.Inky;
import ru.nsu.titov.model.ghosts.Pinky;
import ru.nsu.titov.model.pacman.Pacman;
import ru.nsu.titov.model.playfield.FieldLoader;
import ru.nsu.titov.model.playfield.GameField;
import ru.nsu.titov.model.playfield.Void;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static ru.nsu.titov.model.GameObject.checkCollision;

public class Model {
    //fuck cockals, all my homies use goballs
    private static Model instance = null;
    private static final Logger logger = Logger.getLogger(Model.class);


    private final HashMap<Integer, GameObject> activeObjects = new HashMap<>();
    private GameField gameField;
    private ArrayList<GameObject> ghosts = new ArrayList<>();
    private final Pacman pacman = new Pacman(0, 0);

    int currentScore = 0;

    private Model() {
    }


    public void init() {

        try {
            gameField = FieldLoader.loadMap(GameSession.getInstance().levelName);
        } catch (IOException e) {
            //TODO add default map loading
            logger.warn(String.format("Unable to load map: %s, loading default", GameSession.getInstance().levelName));
            return;
        }
        pacman.setX(29);
        pacman.setY(15);
        pacman.setDirection(Direction.RIGHT);

        System.out.println(gameField.pacmanStartX);
        System.out.println(gameField.pacmanStartY);
        ghosts.add(new Blinky(gameField.blinkyStartX, gameField.blinkyStartY, 15));
        ghosts.add(new Pinky(gameField.pinkyStartX, gameField.pinkyStartY, 15));
        ghosts.add(new Inky(gameField.inkyStartX, gameField.inkyStartY, 15));
        ghosts.add(new Clyde(gameField.blinkyStartX, gameField.blinkyStartY, 15));
        pacman.setVelocity(10);

        activeObjects.clear();
        gameField.getAll().forEach(e -> activeObjects.put(e.getUniqueId(), e));
        activeObjects.put(pacman.getUniqueId(), pacman);
        ghosts.forEach(e -> activeObjects.put(e.getUniqueId(), e));

    }


    /**
     * 1) move pacman
     * 2) check collisions
     * 3) process collisions
     * 4) move others (ghosts)
     * 5) try to generate cherry
     * 6) check finish
     */
    public boolean tick() {
        if (gameField.acceptMove(pacman.getNextX(), pacman.getNextY())) {
            pacman.tick(this);
        }

        gameField.getObjectAt(pacman.getX(), pacman.getY()).onCollide(pacman, this);
        if (gameField.getObjectAt(pacman.getX(), pacman.getY()).getID() == ObjectId.FOOD) {
            activeObjects.remove(gameField.getObjectAt(pacman.getX(), pacman.getY()).getUniqueId());
            gameField.setObjectAt(new Void(pacman.getX(), pacman.getY()), pacman.getX(), pacman.getY());
        }

        ghosts.forEach(ghost -> {
            if (checkCollision(ghost, pacman)) {
                ghost.onCollide(pacman, this);
            }
        });

        ghosts.forEach(ghost -> ghost.tick(this));

        ghosts.forEach(ghost -> gameField.normalizeCoords(ghost));
        gameField.normalizeCoords(pacman);

        System.out.println(pacman.ticksPassed);
        return (gameField.getFoodsLeft() != 0 && pacman.getLives() >= 0);
    }


    public void setPacmanDirection(Direction direction) {
//        Direction backup = pacman.getDirection();
        pacman.setDirection(direction);
//        if (!gameField.acceptMove(pacman.getNextX(), pacman.getNextY())) {
//            pacman.setDirection(backup);
//        }
    }


    public HashMap<Integer, GameObject> getActiveObjects() {
        if (instance == null) {
            instance = new Model();
        }
        return instance.activeObjects;
    }

    public int getCurrentScore() {
        return currentScore;
    }

    public void increaseScore(int delta) {
        currentScore += delta;
    }

    public GameObject getPacmanPosition() {
        return pacman;
    }

    public GameObject getBlinkyPosition() {
        return ghosts.get(0);
    }

    public GameField getGameField() {
        return gameField;
    }

    public static Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    //TODO finish mode
    public void setPacmanCharged(boolean b) {

    }
}
