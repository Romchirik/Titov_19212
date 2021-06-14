package com.consprod;

import java.util.concurrent.ArrayBlockingQueue;

public class Chef implements Runnable{

    int delay = 500;
    ArrayBlockingQueue<Meal> output;

    public void setOutput(ArrayBlockingQueue<Meal> output) {
        this.output = output;
    }

    @Override
    public void run() {

        while (true){
            output.add(new Meal());
            System.out.println("Meal produced");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
