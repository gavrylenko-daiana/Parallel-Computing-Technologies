package eighth;

import eighth.Interface.CounterInterface;

public class Counter implements CounterInterface {
    private int count = 0;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public int getValue() {
        return count;
    }
}
