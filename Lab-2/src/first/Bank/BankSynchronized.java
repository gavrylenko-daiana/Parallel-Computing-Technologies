package first.Bank;

import first.Interface.BankInterface;

public class BankSynchronized implements BankInterface {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long ntransacts = 0;

    public BankSynchronized(int n, int initialBalance) {
        accounts = new int[n];
        for (int i = 0; i < accounts.length; i++)
            accounts[i] = initialBalance;
        ntransacts = 0;
    }

    public synchronized void transfer(int from, int to, int amount) throws InterruptedException {
        if (accounts[from] < amount) return;
        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        if (ntransacts % NTEST == 0) test();
    }

    public synchronized void test() {
        int sum = 0;
        for (int i = 0; i < accounts.length; i++)
            sum += accounts[i];
        System.out.println("Transactions: " + ntransacts + " Sum: " + sum);
    }

    public int size() {
        return accounts.length;
    }
}