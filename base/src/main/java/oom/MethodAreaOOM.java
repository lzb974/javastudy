package oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * 方法区内存溢出
 * -XX:PermSize=16M -XX:MaxPermSize=16M
 * java8 -XX:MetaspaceSize=16M -XX:MaxMetaspaceSize=16M
 * @author jiangtong
 */
public class MethodAreaOOM {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        while (true) {
            System.out.println("=========================");
            Enhancer enhancer = new Enhancer();
            enhancer.setInterfaces(new Class[]{IWork.class});
            enhancer.setUseCache(false);
            enhancer.setCallback(
                    //内部一个接口
                    new NoOp() {
                    }
            );
            try {
                enhancer.create();
            } catch ( Exception e1 ) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        }
    }

    private interface IWork {
    }
}

