package com.consprod;

import java.util.concurrent.ArrayBlockingQueue;

public class Restaurant {
    public static void main(String[] args) {
        ArrayBlockingQueue<Meal> exchangeWindow = new ArrayBlockingQueue<>(20);
        Chef chef = new Chef();
        Waiter waiter = new Waiter();

        chef.setOutput(exchangeWindow);
        waiter.setInput(exchangeWindow);

        Thread chefThread = new Thread(chef, "chef");
        Thread waiterThread = new Thread(waiter, "waiter");

        chefThread.start();
        waiterThread.start();

        try {
            chefThread.join();
            waiterThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
