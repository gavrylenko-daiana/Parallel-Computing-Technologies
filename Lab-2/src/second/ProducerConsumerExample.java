package second;

public class ProducerConsumerExample {
    public static void main(String[] args) {
        int size = 100;
        Drop drop = new Drop(size);

        Thread producer = new Thread(new Producer(drop, size));
        Thread consumer = new Thread(new Consumer(drop));

        producer.start();
        consumer.start();
    }
}
