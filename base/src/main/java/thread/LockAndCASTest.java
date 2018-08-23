package thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class LockAndCASTest {
    final static int THREAD_NUMBER = 1000;
    static ReentrantLock lock = new ReentrantLock();

    public static void test(int loopNumber) throws InterruptedException {
        System.out.println("--------------------loop number:" + loopNumber + "------------------");
        LockRunnable.i = 0;
        Date begin = new Date();
        List<Thread> ts = new ArrayList<>();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            Thread t = new Thread(new LockRunnable(lock, loopNumber));
            ts.add(t);
            t.start();
        }

        for (Thread t : ts) {
            t.join();
        }

        System.out.println("--------------------thread.LockRunnable--------------");
        System.out.println(LockRunnable.i);
        System.out.println(System.currentTimeMillis() - begin.getTime());
        begin = new Date();
        ts.clear();
        AtomicInteger ai = new AtomicInteger(0);
        for (int i = 0; i < THREAD_NUMBER; i++) {
            Thread t = new Thread(new CASRunnable(ai, loopNumber));
            ts.add(t);
            t.start();
        }

        for (Thread t : ts) {
            t.join();
        }

        System.out.println("--------------------thread.CASRunnable--------------");
        System.out.println(ai.get());
        System.out.println(System.currentTimeMillis() - begin.getTime());
    }

    public static void main(String[] args) throws InterruptedException {
        test(100000);
        test(1000000);
    }
}
