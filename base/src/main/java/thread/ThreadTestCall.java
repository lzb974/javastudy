package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadTestCall {
    public static void main(String[] args) {
        Callable<Integer> myCallable = new MyCallable();
        FutureTask<Integer> ft = new FutureTask<Integer>(myCallable);
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
            if (i == 30) {
                Thread thread = new Thread(ft);
                thread.start();
            }

        }
        System.out.println("主线程for循环执行完毕..");

        int sum = 0;
        try {
            sum = ft.get();
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        } catch ( ExecutionException e ) {
            e.printStackTrace();
        }
        System.out.println("sum = " + sum);

    }
}
