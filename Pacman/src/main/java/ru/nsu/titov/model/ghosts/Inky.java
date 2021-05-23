package ru.nsu.titov.model.ghosts;

import ru.nsu.titov.model.Direction;
import ru.nsu.titov.model.Model;
import ru.nsu.titov.model.ObjectId;

public class Inky extends Ghost {

    public Inky(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        ID = ObjectId.INKY;
        this.direction = Direction.LEFT;
        scatterX = 30;
        scatterY = 33;
    }

    public Inky(int logicalX, int logicalY, int velocity) {
        super(logicalX, logicalY);
        ID = ObjectId.INKY;
        this.velocity = velocity;
        this.direction = Direction.LEFT;
        scatterX = 30;
        scatterY = 33;
        this.startVelocity = velocity;
    }


    @Override
    void updateChaseTactic(Model model) {
        int blinkyX = model.getBlinkyPosition().getX();
        int blinkyY = model.getBlinkyPosition().getY();

        int pacX = model.getPacmanPosition().getX();
        int pacY = model.getPacmanPosition().getY();

        int shiftX = 0;
        int shiftY = 0;

        switch (model.getPacmanPosition().getDirection()) {
            case LEFT -> pacX -= 2;
            case RIGHT -> pacX += 2;
            case DOWN -> pacY += 2;
            case UP -> {
                pacX -= 2;
                pacY -= 2;
            }
        }

        shiftX = pacX - blinkyX;
        shiftY = pacY - blinkyY;

        this.direction = getPreferredDirection(pacX + shiftX, pacY + shiftY, model.getGameField());
    }
}
