package jvm;

/**
 * @Description
 * @Author lzb
 * @Date 2021/5/28 9:35
 * @Version 1.0
 */
public class TestCode {
    public int foo() {
        int x;
        try {
            x = 1;
            return x;
        } catch (Exception e) {
            x = 2;
            return x;
        } finally {
            x = 3;
        }
    }
}
