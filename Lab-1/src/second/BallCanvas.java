package second;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BallCanvas extends JPanel {
    private ArrayList<Ball> balls = new ArrayList<>();
    private BounceFrame parentFrame;

    public BallCanvas(BounceFrame frame) {
        this.parentFrame = frame;
    }

    public void add(Ball b) {
        this.balls.add(b);
    }

    public void removeBall(Ball b) {
        balls.remove(b);
        repaint();
    }

    public void updateScore() {
        parentFrame.incrementScore();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.RED);
        g2.fillOval(25, 25, 50, 50); // Червоне коло (луза)

        for (Ball b : balls) {
            b.draw(g2);
        }
    }
}
