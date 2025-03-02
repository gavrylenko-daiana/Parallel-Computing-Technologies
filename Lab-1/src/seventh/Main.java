package seventh;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter();

        CounterThread incrementThread = new CounterThread(counter, true);
        CounterThread decrementThread = new CounterThread(counter, false);

        incrementThread.start();
        decrementThread.start();

        try {
            incrementThread.join();
            decrementThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final counter value: " + counter.getValue());
    }
}
