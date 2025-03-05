package first.Bank;

import first.Interface.BankInterface;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class BankWithAtomic implements BankInterface {
    public static final int NTEST = 10000;
    private final AtomicIntegerArray accounts;
    private long ntransacts = 0;

    public BankWithAtomic(int n, int initialBalance) {
        accounts = new AtomicIntegerArray(n);
        for (int i = 0; i < n; i++)
            accounts.set(i, initialBalance);
        ntransacts = 0;
    }

    public void transfer(int from, int to, int amount) {
        if (accounts.get(from) < amount) return;
        accounts.addAndGet(from, -amount);
        accounts.addAndGet(to, amount);
        ntransacts++;
        if (ntransacts % NTEST == 0) test();
    }

    public void test() {
        int sum = 0;
        for (int i = 0; i < accounts.length(); i++)
            sum += accounts.get(i);
        System.out.println("Transactions: " + ntransacts + " Sum: " + sum);
    }

    public int size() {
        return accounts.length();
    }
}