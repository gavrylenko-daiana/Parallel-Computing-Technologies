package first.Interface;

public interface BankInterface {
    void transfer(int from, int to, int amount) throws InterruptedException;
    void test();
    int size();
}