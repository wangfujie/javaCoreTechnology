package collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 集合类
 * @author wangfujie
 */
public class CollectionTest {

    public static void main(String[] args) {
        CollectionTest.testHashMap();
    }

    public static void testHashMap(){
        //使HashMap变成线程安全的，synchronizedMap方法返回一个线程安全的map
        Map<String, Object> map = Collections.synchronizedMap(new HashMap<>(10000));

        for (int i = 0 ;i < 10000 ; i++){
            String finalI = String.valueOf(i);
            new Thread(() -> {
                map.put(finalI, finalI);
            }).start();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true){
            System.out.println(map.size());
        }
    }

    public static List<String> testList(){
        //使用Collections.synchronizedList(arrayList)可以返回一个同步的ArrayList
        List<String> synList = Collections.synchronizedList(new ArrayList<>());
        //线程安全的List
        List<String> list = new CopyOnWriteArrayList<>();
        list.add("AAA");
        list.add("BBB");
        list.add("CCC");
        list.add("DDD");
        list.forEach(value -> {
            System.out.println("值：" + value);
        });
        return list;
    }

    public static void testSet(){
        //有顺序但不重复的set集合，它继承于HashSet，仅仅比HashSet多个有序
        Set<String> set = new LinkedHashSet<>();
        set.add("AAA");
        set.add("BBB");
        set.add("AAA");
        set.add("CCC");
        set.add("DDD");
        //jdk1.8 的遍历
        set.forEach(System.out::println);
    }

    public static void testMap(){
        //有序的Map，LinkedHashMap (它是HashMap的子类，继承了HashMap)
        Map<String, Object> map = new LinkedHashMap<>(16);
        map.put("AAA","1");
        map.put("BBB","2");
        map.put("CCC","3");
        map.put("DDD","4");
        map.put("EEE","5");
        map.put("FFF","6");
        map.put("GGG","7");
        Set<String> keySet = map.keySet();
        //jdk1.8新特性forEach循环
        keySet.forEach(key -> {
            System.out.println(key + " <--> " + map.get(key));
        });
    }
}
