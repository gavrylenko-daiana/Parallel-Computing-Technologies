package second;

import java.util.Random;

class Producer implements Runnable {
    private final Drop drop;
    private final int size;

    public Producer(Drop drop, int size) {
        this.drop = drop;
        this.size = size;
    }

    public void run() {
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int value = random.nextInt(1000);
            drop.put(value);
            System.out.println("Produced: " + value);
            try {
                Thread.sleep(random.nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        drop.put(-1);
    }
}