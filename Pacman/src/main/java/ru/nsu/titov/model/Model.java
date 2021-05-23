package ru.nsu.titov.model;


import org.apache.log4j.Logger;
import ru.nsu.titov.controller.GameSession;
import ru.nsu.titov.model.ghosts.*;
import ru.nsu.titov.model.pacman.Pacman;
import ru.nsu.titov.model.playfield.FieldLoader;
import ru.nsu.titov.model.playfield.GameField;
import ru.nsu.titov.model.playfield.Void;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static ru.nsu.titov.model.GameObject.checkCollision;

public final class Model {
    //fuck cockals, all my homies use goballs
    private static Model instance = null;
    private static final Logger logger = Logger.getLogger(Model.class);

    private Direction pacNextDir = Direction.UNDEFINED;
    private final HashMap<Integer, GameObject> activeObjects = new HashMap<>();
    private GameField gameField;
    private ArrayList<Ghost> ghosts = new ArrayList<>();
    private final Pacman pacman = new Pacman(0, 0);

    private GhostState ghostsStates = GhostState.SCATTER;
    private int rageOn = 0;
    int currentScore = 0;

    private Model() {
    }


    public void init() {
        activeObjects.clear();
        ghosts.clear();

        try {
            gameField = FieldLoader.loadMap(GameSession.getInstance().levelName);
        } catch (IOException e) {
            //TODO add default map loading
            logger.warn(String.format("Unable to load map: %s, loading default", GameSession.getInstance().levelName));
            return;
        }

        pacman.setX(gameField.pacmanStartX);
        pacman.setY(gameField.pacmanStartY);
        pacman.setDirection(Direction.RIGHT);
        pacman.setLives(3);

        ghosts.add(new Blinky(gameField.blinkyStartX, gameField.blinkyStartY, 10));
        ghosts.get(0).setDirection(Direction.RIGHT);
        ghosts.add(new Pinky(gameField.pinkyStartX, gameField.pinkyStartY, 10));
        ghosts.get(1).setDirection(Direction.RIGHT);
        ghosts.add(new Inky(gameField.inkyStartX, gameField.inkyStartY, 10));
        ghosts.get(2).setDirection(Direction.LEFT);
        ghosts.add(new Clyde(gameField.clydeStartX, gameField.clydeStartY, 10));
        ghosts.get(3).setDirection(Direction.LEFT);
        pacman.setVelocity(10);


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

        if (pacNextDir != Direction.UNDEFINED && pacman.getTicksPassed() == 0) {
            Direction backup = pacman.getDirection();
            pacman.setDirection(pacNextDir);
            if (!gameField.acceptMove(pacman.getX(), pacman.getY(), pacman.getNextX(), pacman.getNextY(), ObjectId.PACMAN)) {
                pacman.setDirection(backup);
            }
            pacNextDir = Direction.UNDEFINED;
        }

        if (gameField.acceptMove(pacman.getX(), pacman.getY(), pacman.getNextX(), pacman.getNextY(), ObjectId.PACMAN)) {
            pacman.tick(this);
        }

        gameField.getObjectAt(pacman.getX(), pacman.getY()).onCollide(pacman, this);
        if (gameField.getObjectAt(pacman.getX(), pacman.getY()).getID() == ObjectId.FOOD ||
                gameField.getObjectAt(pacman.getX(), pacman.getY()).getID() == ObjectId.ENERGIZER) {
            activeObjects.remove(gameField.getObjectAt(pacman.getX(), pacman.getY()).getUniqueId());
            gameField.setObjectAt(new Void(pacman.getX(), pacman.getY()), pacman.getX(), pacman.getY());
        }

        ghosts.forEach(ghost -> {
            if (checkCollision(ghost, pacman)) {
                ghost.onCollide(pacman, this);
            }
            gameField.getObjectAt(ghost.getX(), ghost.getY()).onCollide(ghost, this);
        });


        ghosts.forEach(ghost -> ghost.tick(this));


        if (rageOn > 1) {
            rageOn--;
        }
        if (rageOn == 1) {
            rageOn--;
            ghosts.forEach(ghost ->
                    ghost.setState(GhostState.SCATTER)
            );
        }
        ghosts.forEach(ghost -> gameField.normalizeCoords(ghost));
        gameField.normalizeCoords(pacman);
        return (gameField.getFoodsLeft() != 0 && pacman.getLives() >= 0);
    }


    public void setPacmanDirection(Direction direction) {
        if (Direction.getOppositeDir(direction) == pacman.getDirection()) {
            pacman.setDirection(direction);
            return;
        }
        pacNextDir = direction;
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

    public int getPacmanLives() {
        return pacman.getLives();
    }

    public void setPacmanEaten() {
        ghosts.forEach(Ghost::reset);
        pacman.setDirection(Direction.RIGHT);
        pacman.reset();
        pacman.setX(gameField.pacmanStartX);
        pacman.setY(gameField.pacmanStartY);
        pacman.setLives(pacman.getLives() - 1);
    }

    public void setRageMode(int rageDuration) {
        rageOn = rageDuration;
        ghosts.forEach(ghost -> ghost.setState(GhostState.FRIGHTENED));
    }

    public void flipScatterChase() {
        if (rageOn > 0) {
            return;
        }
        if (ghostsStates == GhostState.CHASE) {
            ghostsStates = GhostState.SCATTER;
            ghosts.forEach(ghost -> ghost.setState(ghostsStates));
        } else {
            ghostsStates = GhostState.CHASE;
            ghosts.forEach(ghost -> ghost.setState(ghostsStates));
        }
    }
}
