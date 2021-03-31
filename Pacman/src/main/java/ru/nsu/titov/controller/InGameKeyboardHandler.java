package ru.nsu.titov.controller;

import ru.nsu.titov.model.Direction;
import ru.nsu.titov.model.ModelController;
import ru.nsu.titov.view.MainWindow;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class InGameKeyboardHandler extends KeyAdapter {
    ModelController associatedModelController;

    public InGameKeyboardHandler(ModelController hui) {
        associatedModelController = hui;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP){
            associatedModelController.setPacmanDirection(Direction.UP);
        } else if (code == KeyEvent.VK_DOWN){
            associatedModelController.setPacmanDirection(Direction.DOWN);
        } else if (code == KeyEvent.VK_LEFT){
            associatedModelController.setPacmanDirection(Direction.LEFT);
        } else if (code == KeyEvent.VK_RIGHT){
            associatedModelController.setPacmanDirection(Direction.RIGHT);
        } else if(code == KeyEvent.VK_ESCAPE){
            associatedModelController.finishSession();
        }
    }


}
