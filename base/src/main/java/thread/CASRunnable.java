package thread;

import java.util.concurrent.atomic.AtomicInteger;

// 使用AtomicInteger，其底层实现是CAS
class CASRunnable implements Runnable {

    private AtomicInteger ai;

    private int loopNumber;

    public CASRunnable(AtomicInteger ai, int loopNumber) {
        this.ai = ai;
        this.loopNumber = loopNumber;
    }

    @Override
    public void run() {
        // 将incrementAndGet放在for循环内部，从而让CAS激烈，同时与Lock的数量保持一致
        for (int j = 0; j < loopNumber; j++) {
            ai.incrementAndGet();
        }
    }

}
