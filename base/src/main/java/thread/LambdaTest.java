package thread;

/**
 * @Description
 * @Author lzb
 * @Date 2021/5/27 10:37
 * @Version 1.0
 */
public class LambdaTest {
    public static void main(String[] args) {
        new Thread(()->System.out.println( Thread.currentThread().getId()+"=======================In Java8, Lambda expression rocks !")).start();
        new Thread(()->System.out.println(Thread.currentThread().getId()+"=======================In Java8, Lambda expression rocks !")).start();
        new Thread(()->System.out.println(Thread.currentThread().getId()+"=======================In Java8, Lambda expression rocks !")).start();
        //System.out.println("=======================In Java8, Lambda expression rocks !");
    }
}
