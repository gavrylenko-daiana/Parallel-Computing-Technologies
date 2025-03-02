package fourth;

public class BallThread extends Thread {
    private Ball b;

    public BallThread(Ball ball) {
        this.b = ball;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 200; i++) {
                b.move();
                System.out.println("Thread name = "
                        + Thread.currentThread().getName());
                Thread.sleep(5);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}


