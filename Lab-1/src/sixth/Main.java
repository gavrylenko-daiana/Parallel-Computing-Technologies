package sixth;

public class Main {
    public static void main(String[] args) {
        SymbolPrinter printer = new SymbolPrinter();

        Thread dashThread = new Thread(() -> printer.printSymbol('-', true));
        Thread pipeThread = new Thread(() -> printer.printSymbol('|', false));

        dashThread.start();
        pipeThread.start();
    }
}

