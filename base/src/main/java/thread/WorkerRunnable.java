package thread;

import java.util.concurrent.CountDownLatch;

public class WorkerRunnable implements Runnable{
    private final CountDownLatch doneSignal;
    private final int i;

    WorkerRunnable(CountDownLatch doneSignal, int i) {
        this.doneSignal = doneSignal;
        this.i = i;
    }

    @Override
    public void run() {
        //子线程的任务
        try {
            doWork(i);
        } catch ( Exception e ) {
            e.printStackTrace();
        }
        //任务执行完毕递减锁存器的计数
        doneSignal.countDown();
    }

    void doWork(int i) {
        System.out.println("这是第" + (i + 1) + "个任务");
    }
}

