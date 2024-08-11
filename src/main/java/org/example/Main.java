package org.example;

public class Main {
    public static void main(String[] args) {
        final int numPhilosophers = 5;
        Philosopher[] philosophers = new Philosopher[numPhilosophers];
        Fork[] forks = new Fork[numPhilosophers];

        for (int i = 0; i < numPhilosophers; i++) {
            forks[i] = new Fork(i);
        }

        for (int i = 0; i < numPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i + 1) % numPhilosophers]);
            new Thread(philosophers[i]).start();
        }
    }
}