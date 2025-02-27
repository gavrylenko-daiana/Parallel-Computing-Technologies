package third;

public class Bounce {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            BounceFrame frame = new BounceFrame();
            frame.setVisible(true);
        });
    }
}