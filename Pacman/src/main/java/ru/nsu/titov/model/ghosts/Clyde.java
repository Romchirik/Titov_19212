package ru.nsu.titov.model.ghosts;

import ru.nsu.titov.model.Direction;
import ru.nsu.titov.model.Model;
import ru.nsu.titov.model.ObjectId;

public class Clyde extends Ghost {

    public Clyde(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        ID = ObjectId.CLYDE;
        this.direction = Direction.LEFT;

        scatterX = 0;
        scatterY = 33;
    }

    public Clyde(int logicalX, int logicalY, int velocity) {
        super(logicalX, logicalY);
        ID = ObjectId.CLYDE;
        this.velocity = velocity;
        this.direction = Direction.LEFT;
        scatterX = 0;
        scatterY = 33;
    }

    @Override
    void updateChaseTactic(Model model) {
        int pacX = model.getPacmanPosition().getX();
        int pacY = model.getPacmanPosition().getY();

        if (Math.sqrt(getCubedDistance(x, y, pacX, pacY)) < 8) {
            direction = getPreferredDirection(scatterX, scatterY, model.getGameField());
        } else {
            direction = getPreferredDirection(pacX, pacY, model.getGameField());
        }


    }
}
