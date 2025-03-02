package eighth;

import eighth.Interface.CounterInterface;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        testCounter(new Counter(), "Without synchronization: ");
        testCounter(new SynchronizedCounter(), "Synchronized method: ");
        testCounter(new BlockSynchronizedCounter(), "Synchronized block: ");
        testCounter(new LockCounter(), "ReentrantLock: ");
    }

    private static void testCounter(CounterInterface counter, String testName) throws InterruptedException {
        Thread incThread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.increment();
            }
        });

        Thread decThread = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                counter.decrement();
            }
        });

        incThread.start();
        decThread.start();

        incThread.join();
        decThread.join();

        System.out.println(testName + counter.getValue());
    }
}

