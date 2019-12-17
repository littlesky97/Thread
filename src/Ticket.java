import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ticket {

    public static void main(String[] args) {
        Thread1 thread1 = new Thread1();
        new Thread(() -> {
            for (int i = 1; i <= 105; i++) {
                thread1.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 105; i++) {
                thread1.sale();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 105; i++) {
                thread1.sale();
            }
        }, "C").start();
    }
}

class Thread1 {
    Lock lock = new ReentrantLock();
    private int num = 100;

    public void sale() {
        lock.lock();
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + (num--) + "还剩" + num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}