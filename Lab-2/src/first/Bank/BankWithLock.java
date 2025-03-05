package first.Bank;

import first.Interface.BankInterface;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankWithLock implements BankInterface {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long ntransacts = 0;
    private final Lock bankLock = new ReentrantLock();

    public BankWithLock(int n, int initialBalance) {
        accounts = new int[n];
        for (int i = 0; i < accounts.length; i++)
            accounts[i] = initialBalance;
        ntransacts = 0;
    }

    public void transfer(int from, int to, int amount) throws InterruptedException {
        bankLock.lock();
        try {
            if (accounts[from] < amount) return;
            accounts[from] -= amount;
            accounts[to] += amount;
            ntransacts++;
            if (ntransacts % NTEST == 0) test();
        } finally {
            bankLock.unlock();
        }
    }

    public void test() {
        int sum = 0;
        for (int i = 0; i < accounts.length; i++)
            sum += accounts[i];
        System.out.println("Transactions: " + ntransacts + " Sum: " + sum);
    }

    public int size() {
        return accounts.length;
    }
}