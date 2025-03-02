package eighth;

import eighth.Interface.CounterInterface;

public class SynchronizedCounter implements CounterInterface {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public synchronized void decrement() {
        count--;
    }

    public synchronized int getValue() {
        return count;
    }
}

