package oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 运行时常量池溢出
 * -XX:PermSize=10M -XX:MaxPermSize=10M -XX:+HeapDumpOnOutOfMemoryError
 * @author jiangtong
 *
 */
public class ConstanOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        int i=0;
        while(true){
            list.add(String.valueOf(i++).intern());
            System.out.println(i);
        }
    }
}
/**
 *
 * 功能描述: 运行时常量池溢出
 * 向运行时常量池中添加内从，最简单的做法就是使用String.intern()这个Native方法。该方法的作用是：如果池中已经包含一个等于此String对象的字符串，则返回代表池中这个字符串的String对象；否则将此String对象包含的字符串添加到常量池中，并且返回此String对象的引用
 * @param:
 * @return: 
 * @auther: dell
 * @date: 2019/1/11 16:54
 */
