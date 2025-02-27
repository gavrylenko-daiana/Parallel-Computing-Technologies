package third;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class BounceFrame extends JFrame {
    private BallCanvas canvas;
    private int threadCounter = 0;

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    public BounceFrame() {
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("Priority Ball Experiment");

        this.canvas = new BallCanvas();
        Container content = this.getContentPane();
        content.add(this.canvas, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.lightGray);

        JButton button2 = new JButton("2 кульок");
        JButton button100 = new JButton("100 кульок");
        JButton button500 = new JButton("500 кульок");
        JButton buttonStop = new JButton("Stop");

        button2.addActionListener(e -> createBalls(2));
        button100.addActionListener(e -> createBalls(100));
        button500.addActionListener(e -> createBalls(500));

        buttonStop.addActionListener(e -> {
            dispose();
            System.exit(0);
        });

        buttonPanel.add(button2);
        buttonPanel.add(button100);
        buttonPanel.add(button500);
        buttonPanel.add(buttonStop);
        content.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void createBalls(int number) {
        Random rand = new Random();
        int startX = rand.nextInt(this.canvas.getWidth() - 20);
        int startY = rand.nextInt(this.canvas.getHeight() - 20);

        System.out.println("Створюємо " + number + " кульок з позиції (" + startX + ", " + startY + ")");

        for (int i = 0; i < number; i++) {
            Ball b = new Ball(canvas, startX, startY, Color.BLUE);
            canvas.add(b);
            BallThread thread = new BallThread(b);
            thread.setName("BallThread-" + (++threadCounter));
            thread.start();
        }

        Ball redBall = new Ball(canvas, startX, startY, Color.RED);
        canvas.add(redBall);
        BallThread redThread = new BallThread(redBall);
        redThread.setName("BallThread-" + (++threadCounter));
        redThread.start();
    }
}
