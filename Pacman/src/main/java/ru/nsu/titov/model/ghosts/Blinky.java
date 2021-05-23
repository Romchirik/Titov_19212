package ru.nsu.titov.model.ghosts;

import ru.nsu.titov.model.Direction;
import ru.nsu.titov.model.Model;
import ru.nsu.titov.model.ObjectId;

import java.util.List;

public class Blinky extends Ghost {


    public Blinky(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        ID = ObjectId.BLINKY;
        this.direction = Direction.RIGHT;
        scatterX = 30;
        scatterY = 0;
    }

    public Blinky(int logicalX, int logicalY, int velocity) {
        super(logicalX, logicalY);
        ID = ObjectId.BLINKY;
        this.velocity = velocity;
        this.startVelocity = velocity;
        this.direction = Direction.RIGHT;
        scatterX = 30;
        scatterY = 0;
    }

    @Override
    void updateChaseTactic(Model model) {
        this.direction = getPreferredDirection(model.getPacmanPosition().getX(), model.getPacmanPosition().getY(), model.getGameField());
    }


}
