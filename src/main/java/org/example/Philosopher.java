package org.example;

public class Philosopher implements Runnable{
    private final int id;
    private final Fork leftFork;
    private final Fork rightFork;

    public Philosopher(int id, Fork leftFork, Fork rightFork) {
        this.id = id;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void think() throws InterruptedException {
        System.out.println("Философ " + id + " размышляет.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    private void eat() throws InterruptedException {
        System.out.println("Философ " + id + " ест.");
        Thread.sleep((long) (Math.random() * 1000));
    }

    @Override
    public void run() {
        try {
            while (true) {
                think();
                if (id % 2 == 0) {
                    synchronized (leftFork) {
                        leftFork.pickUp();
                        System.out.println("Философ " + id + " взял левую вилку " + leftFork);

                        synchronized (rightFork) {
                            rightFork.pickUp();
                            System.out.println("Философ " + id + " взял правую вилку " + rightFork);

                            eat();

                            rightFork.putDown();
                            System.out.println("Философ " + id + " положил правую вилку " + rightFork);
                        }

                        leftFork.putDown();
                        System.out.println("Философ " + id + " положил левую вилку " + leftFork);
                    }
                } else {
                    synchronized (rightFork) {
                        rightFork.pickUp();
                        System.out.println("Философ " + id + " взял правую вилку " + rightFork);

                        synchronized (leftFork) {
                            leftFork.pickUp();
                            System.out.println("Философ " + id + " взял левую вилку " + leftFork);

                            eat();

                            leftFork.putDown();
                            System.out.println("Философ " + id + " положил левую вилку " + leftFork);
                        }

                        rightFork.putDown();
                        System.out.println("Философ " + id + " положил правую вилку " + rightFork);
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }
}
