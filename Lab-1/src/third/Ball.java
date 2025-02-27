package third;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class Ball {
    private BallCanvas canvas;
    private static final int SIZE = 20;
    private int x, y;
    private int dx = 2;
    private int dy = 2;
    private Color color;

    public Ball(BallCanvas c, int startX, int startY, Color color) {
        this.canvas = c;
        this.x = startX;
        this.y = startY;
        this.color = color;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fill(new Ellipse2D.Double(x, y, SIZE, SIZE));
    }

    public void move() {
        x += dx;
        y += dy;

        if (x < 0 || x + SIZE > canvas.getWidth()) dx = -dx;
        if (y < 0 || y + SIZE > canvas.getHeight()) dy = -dy;

        this.canvas.repaint();
    }

    public Color getColor() {
        return color;
    }
}
