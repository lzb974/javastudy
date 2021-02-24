package collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class TestShuffle {
    // 打乱列表实现方法1
    public <T> void shuffle1(List<T> list) {
        int size = list.size();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            // 获取随机位置
            int randomPos = random.nextInt(size);

            // 当前元素与随机元素交换
            T temp = list.get(i);
            list.set(i, list.get(randomPos));
            list.set(randomPos, temp);
        }
    }

    // 打乱列表实现方法2
    public <T> void shuffle2(List<T> list) {
        int size = list.size();
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            // 获取随机位置
            int randomPos = random.nextInt(size);

            // 当前元素与随机元素交换
            Collections.swap(list, i, randomPos);
        }
    }

    // 打乱列表实现方法3
    public <T> void shuffle3(List<T> list) {
        // 打乱顺序
        Collections.shuffle(list);
    }

    // 打印列表
    public <T> void print(List<T> list) {
        for (T t : list) {
            System.out.print(t + " ");
        }

        System.out.println("\n");
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        TestShuffle st = new TestShuffle();
        List<String> tagClouds = new ArrayList<String>(6);

        // 一般从数据库中读取，这里仅以测试为目的
        tagClouds.add("计算机");
        tagClouds.add("Java");
        tagClouds.add("编程");
        tagClouds.add("C/C++");
        tagClouds.add("操作系统");
        tagClouds.add("数据库");
        System.out.println("原顺序：");
        st.print(tagClouds);

        st.shuffle1(tagClouds);
        System.out.println("打乱顺序一：");
        st.print(tagClouds);

        st.shuffle2(tagClouds);
        System.out.println("打乱顺序二：");
        st.print(tagClouds);

        st.shuffle3(tagClouds);
        System.out.println("打乱顺序三：");
        st.print(tagClouds);
    }

}
