import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wangfujie
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public List<String> testList(){
        //线程安全的List
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0 ; i < 10 ; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    public static void testCollection(){
        //有序的set集合，它继承于HashSet，仅仅比HashSet多个有序
        Set<String> set = new LinkedHashSet<>();
        for (int i = 0 ; i < 1000 ; i++) {
            set.add(String.valueOf(i));
        }
        set.forEach(System.out::println);
    }

    public static void testMap(){
        //有序的Map，LinkedHashMap (它是HashMap的子类，继承了HashMap)
        Map<String, Object> map = new LinkedHashMap<>();
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
