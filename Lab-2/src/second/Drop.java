package second;

class Drop {
    private final int[] buffer;
    private int count = 0;
    private boolean empty = true;

    public Drop(int size) {
        this.buffer = new int[size];
    }

    public synchronized int take() {
        while (empty) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        int value = buffer[--count];
        if (count == 0) empty = true;
        notifyAll();
        return value;
    }

    public synchronized void put(int value) {
        while (!empty && count == buffer.length) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        buffer[count++] = value;
        empty = false;
        notifyAll();
    }
}