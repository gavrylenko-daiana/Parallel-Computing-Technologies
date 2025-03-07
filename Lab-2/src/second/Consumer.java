package second;

import java.util.Random;

class Consumer implements Runnable {
    private final Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        int value;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while ((value = drop.take()) != -1) {
            System.out.println("Consumed: " + value);
            try {
                Thread.sleep(new Random().nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}