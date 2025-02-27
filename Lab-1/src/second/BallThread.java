package second;

public class BallThread extends Thread {
    private Ball b;

    public BallThread(Ball ball) {
        this.b = ball;
    }

    @Override
    public void run() {
        try {
            while (true) {
                synchronized (b) {
                    if (b.isInHole()) {
                        break;
                    }
                }
                b.move();
                Thread.sleep(5);
            }
            System.out.println("Кулька потрапила в лузу! Потік завершено: " + getName());
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
