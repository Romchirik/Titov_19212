package ru.nsu.titov.model.playfield;

import ru.nsu.titov.model.GameObject;
import ru.nsu.titov.model.Model;
import ru.nsu.titov.model.ObjectId;

public class Food extends GameObject {
    private boolean eaten = false;
    final private int COST = 10;

    public Food(int logicalX, int logicalY) {
        super(logicalX, logicalY);
        ID = ObjectId.FOOD;
    }

    @Override
    public void tick(Model model) {

    }

    public boolean isEaten() {
        return eaten;
    }


    @Override
    public void onCollide(GameObject object, Model model) {
        if(object.getID() == ObjectId.PACMAN){
            this.eaten = true;
            model.increaseScore(COST);
        }

    }


}
