package sixth;

class SymbolPrinter {
    private boolean dashTurn = true;

    public synchronized void printSymbol(char symbol, boolean isDash) {
        try {
            for (int i = 0; i < 100; i++) {
                while (dashTurn != isDash) {
                    wait();
                }
                System.out.print(symbol);
                dashTurn = !dashTurn;
                notify();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
