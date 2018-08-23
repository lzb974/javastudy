package thread;

import java.util.concurrent.locks.ReentrantLock;

// 使用ReentrantLock
class LockRunnable implements Runnable {
    static int i = 0;

    private ReentrantLock lock;

    private int loopNumber;

    public LockRunnable(ReentrantLock lock, int loopNumber) {
        this.lock = lock;
        this.loopNumber = loopNumber;
    }

    @Override
    public void run() {

        // 将加锁解锁卸载for放在内部，从而让锁的竞争变得激烈
        for (int j = 0; j < loopNumber; j++) {
            try {
                lock.lock();
                this.i++;
            }
            finally {
                lock.unlock();
            }
        }

    }
}
