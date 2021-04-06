package ru.nsu.titov.view;

import ru.nsu.titov.model.GameObject;

import java.awt.*;

public class MyPainter {
    private Graphics2D graphics;
    private int ticksPassed = 0;

    public MyPainter() {
    }

    public void paint(GameObject object) {

    }

    public void setTicksPassed(int ticksPassed) {
        this.ticksPassed = ticksPassed;
    }

    public void setGraphics(Graphics graphics) {
        this.graphics = (Graphics2D) graphics;
    }
}
