package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * List方法
 *
 * @author wangfj
 */
public class ListTest {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i + "");
        }
        //使用折半查找法，找集合中的特定元素
        System.out.println(Collections.binarySearch(list, "569"));
        Collections.reverse(list);
    }
}
