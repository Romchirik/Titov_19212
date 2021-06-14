package com.consprod;

import java.util.concurrent.ArrayBlockingQueue;

public class Waiter implements Runnable {

    int delay = 20;
    ArrayBlockingQueue<Meal> input;

    public void setInput(ArrayBlockingQueue<Meal> input) {
        this.input = input;
    }

    @Override
    public void run() {
        while(true){
            System.out.println("Waiting for meal");
            while(input.isEmpty()){}

            input.poll();

            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
