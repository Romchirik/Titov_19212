package ru.nsu.titov.controller;

import ru.nsu.titov.model.Direction;
import ru.nsu.titov.model.ModelController;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InGameKeyboardListener extends KeyAdapter {

    ModelController associatedModelController;

    public InGameKeyboardListener(ModelController associatedModelController) {
        this.associatedModelController = associatedModelController;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        //this bullshit doesn't work with switch
        //TODO каким то образом переделать на свитчи
        if(code == KeyEvent.VK_UP){
            associatedModelController.setPacmanDirection(Direction.UP);
        } else if (code == KeyEvent.VK_DOWN){
            associatedModelController.setPacmanDirection(Direction.DOWN);
        } else if (code == KeyEvent.VK_LEFT){
            associatedModelController.setPacmanDirection(Direction.LEFT);
        } else if (code == KeyEvent.VK_RIGHT){
            associatedModelController.setPacmanDirection(Direction.RIGHT);
        }
    }
}
