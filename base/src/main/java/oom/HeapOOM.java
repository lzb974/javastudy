package oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Java堆内存溢出
 * @author jiangtong
 *
 */
public class HeapOOM {
    /**
     * 静态类
     */
    static class OOMObject{

    }
    /**
     * -Xms20M -Xmx20M -Xmn10M -XX:SurvivorRatio=8 -XX:PermSize=32M -XX:MaxPermSize=64M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
     */
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        int count = 0;
        while(true){
            try {
                count++;
                list.add(new OOMObject());
                System.out.println("共构造了"+count+"个对象");
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
/**
 *
 * 功能描述: 内存溢出异常
 * [GC (Heap Inspection Initiated GC) [PSYoungGen: 7071K->1016K(9216K)] 9400K->3648K(19456K), 0.0041334 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
 * [Full GC (Heap Inspection Initiated GC) [PSYoungGen: 1016K->0K(9216K)] [ParOldGen: 2632K->2918K(10240K)] 3648K->2918K(19456K), [Metaspace: 15130K->14822K(1062912K)], 0.0406550 secs] [Times: user=0.09 sys=0.00, real=0.04 secs]
 * @param:
 * @return:
 * @auther: dell
 * @date: 2019/1/11 16:32
 */
