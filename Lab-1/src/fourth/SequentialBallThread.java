package fourth;

public class SequentialBallThread extends BallThread {
    private BallThread previousThread;

    public SequentialBallThread(Ball ball, BallThread previous) {
        super(ball);
        this.previousThread = previous;
    }

    @Override
    public void run() {
        try {
            previousThread.join();
            super.run();
        } catch (InterruptedException ignored) {
            super.interrupt();
        }
    }
}
