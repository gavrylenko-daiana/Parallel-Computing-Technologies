package eighth;

import eighth.Interface.CounterInterface;

class BlockSynchronizedCounter implements CounterInterface {
    private int count = 0;
    private final Object lock = new Object();

    public void increment() {
        synchronized (lock) {
            count++;
        }
    }

    public void decrement() {
        synchronized (lock) {
            count--;
        }
    }

    public int getValue() {
        synchronized (lock) {
            return count;
        }
    }
}
