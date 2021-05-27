package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @Author lzb
 * @Date 2021/5/27 15:12
 * @Version 1.0
 */
public class ReentrantLockTest {
    public static void main(String[] args) {
        Printer printer = new ReentrantLockTest().new Printer();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    printer.print("zhangsan33953");
                }
            }

            ;
        }.start();

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    printer.print("lisi27965");
                }
            }

            ;
        }.start();
    }

    //获得打印控制锁
    class Printer {
        private Lock lock = new ReentrantLock();//默认是非公平锁

        public void print(String name) {
            lock.lock(); // 获取锁 ， 获取不到会阻塞
            try {
                int len = name.length();
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            } finally {
                lock.unlock(); // 释放锁
            }
        }
    }
}
