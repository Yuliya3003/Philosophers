package org.example;

public class Fork {
    private final int id;
    private boolean isTaken;

    public Fork(int id) {
        this.id = id;
        this.isTaken = false;
    }

    public synchronized void pickUp() throws InterruptedException {
        while (isTaken) {
            wait();
        }
        isTaken = true;
    }

    public synchronized void putDown() {
        isTaken = false;
        notifyAll();
    }

    @Override
    public String toString() {
        return "Вилка " + id;
    }
}
