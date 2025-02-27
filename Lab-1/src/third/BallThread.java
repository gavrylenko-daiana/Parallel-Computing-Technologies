package third;

import java.awt.*;

public class BallThread extends Thread {
    private Ball b;

    public BallThread(Ball ball) {
        this.b = ball;
        if (ball.getColor().equals(Color.RED)) {
            setPriority(Thread.MAX_PRIORITY);
        } else {
            setPriority(Thread.MIN_PRIORITY);
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                b.move();
                Thread.sleep(5);
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
