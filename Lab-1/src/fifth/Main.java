package fifth;

public class Main {
    public static void main(String[] args) {
        DashThread dashThread = new DashThread();
        PipeThread pipeThread = new PipeThread();

        dashThread.start();
        pipeThread.start();
    }
}