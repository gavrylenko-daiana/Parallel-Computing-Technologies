package first;

import first.Bank.BankSynchronized;
import first.Bank.BankWithAtomic;
import first.Bank.BankWithLock;
import first.Interface.BankInterface;

public class UnsynchBankTest {
    public static final int NACCOUNTS = 50;
    public static final int INITIAL_BALANCE = 10000;
    public static final String BANK_TYPE = "atomic";

    public static void main(String[] args) {
        BankInterface bank;
        switch (BANK_TYPE) {
            case "lock":
                bank = new BankWithLock(NACCOUNTS, INITIAL_BALANCE);
                break;
            case "atomic":
                bank = new BankWithAtomic(NACCOUNTS, INITIAL_BALANCE);
                break;
            default:
                bank = new BankSynchronized(NACCOUNTS, INITIAL_BALANCE);
        }

        for (int i = 0; i < NACCOUNTS; i++) {
            TransferThread t = new TransferThread(bank, i, INITIAL_BALANCE);
            t.setPriority(Thread.NORM_PRIORITY + i % 2);
            t.start();
        }
    }
}