package seventh;

class CounterThread extends Thread {
    private Counter counter;
    private boolean isIncrement;

    public CounterThread(Counter counter, boolean isIncrement) {
        this.counter = counter;
        this.isIncrement = isIncrement;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            if (isIncrement) {
                counter.increment();
            } else {
                counter.decrement();
            }
        }
    }
}