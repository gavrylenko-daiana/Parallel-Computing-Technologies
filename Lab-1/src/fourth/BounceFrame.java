package fourth;

import javax.swing.*;
import java.awt.*;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 400;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Thread Join Experiment");

        this.canvas = new BallCanvas();
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton buttonStart = new JButton("Start");
        JButton buttonStop = new JButton("Stop");

        buttonStart.addActionListener(e -> startAnimation(canvas));
        buttonStop.addActionListener(e -> System.exit(0));

        buttonPanel.add(buttonStart);
        buttonPanel.add(buttonStop);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void startAnimation(BallCanvas canvas) {
        Ball ball = new Ball(canvas, Color.BLUE);
        canvas.addBall(ball);
        BallThread ballThread = new BallThread(ball);
        ballThread.start();
        BallThread lastThread = ballThread;

        ball = new Ball(canvas, Color.RED);
        canvas.addBall(ball);
        ballThread = new SequentialBallThread(ball, lastThread);
        ballThread.start();
        lastThread = ballThread;

        ball = new Ball(canvas, Color.YELLOW);
        canvas.addBall(ball);
        ballThread = new SequentialBallThread(ball, lastThread);
        ballThread.start();
        lastThread = ballThread;

        ball = new Ball(canvas, Color.GREEN);
        canvas.addBall(ball);
        ballThread = new SequentialBallThread(ball, lastThread);
        ballThread.start();
    }
}
