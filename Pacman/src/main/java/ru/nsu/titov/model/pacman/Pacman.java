package ru.nsu.titov.model.pacman;

import ru.nsu.titov.controller.Settings;
import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.ModelController;
import ru.nsu.titov.model.ObjectId;
import ru.nsu.titov.model.map.Map;

import java.awt.*;

public class Pacman extends GameObject {
    public Pacman(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        ID = ObjectId.PACMAN;
    }


    @Override
    public void tick(ModelController model) {
        switch (direction) {
            case DOWN -> y += velocity;
            case UP -> y -= velocity;
            case LEFT -> x -= velocity;
            case RIGHT -> x += velocity;
        }
        logicalX = x / Settings.CELL_SIZE;
        logicalY = y / Settings.CELL_SIZE;
        logicalX = Map.normalizeLogicalX(logicalX);
        logicalY = Map.normalizeLogicalY(logicalY);

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, Settings.CELL_SIZE, Settings.CELL_SIZE);
    }

    public int getNextLogicalX() {
        return switch (direction) {
            case RIGHT -> Map.normalizeLogicalX(logicalX - 1);
            case DOWN -> Map.normalizeLogicalX(logicalX + 1);
            default -> logicalX;
        };
    }

    public int getNextLogicalY() {
        return switch (direction) {
            case UP -> Map.normalizeLogicalY(logicalY - 1);
            case LEFT -> Map.normalizeLogicalY(logicalY + 1);
            default -> logicalY;
        };
    }
}
