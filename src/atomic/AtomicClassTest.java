package atomic;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 原子操作类
 *
 * @author wangfj
 */
public class AtomicClassTest {

    public static void main(String[] args) {
        //原子更新整型，默认0
        AtomicInteger atomicInteger = new AtomicInteger();
        //如果原值等于expect预期值，则修改值为update，返回true
        System.out.println(atomicInteger.compareAndSet(0, 2));
        System.out.println(atomicInteger.get());

        //原子更新数组整型，默认0
        AtomicIntegerArray atomicArray = new AtomicIntegerArray(5);
        //如果该下标的当前值等于预期值，则以原子方式设置为update
        System.out.println(atomicArray.compareAndSet(0,0,2));
        //指定下标位置，设置新值（原值加上delta值），返回旧值
        System.out.println(atomicArray.getAndAdd(0,1));
        System.out.println(atomicArray.toString());

        //原子更新整型属性
        AtomicIntegerFieldUpdater<User> fieldUpdater = AtomicIntegerFieldUpdater.newUpdater(User.class, "age");
        User user = new User("张三",22);
        //涨了一岁
        fieldUpdater.getAndIncrement(user);
        //如果为23，则改为25
        fieldUpdater.compareAndSet(user,23,25);
        System.out.println(user);
    }
}

class User{
    private String name;
    //被原子操作的属性（AtomicIntegerFieldUpdater），必须用public volatile修饰
    public volatile int age;

    User(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "姓名：" + this.name + " >> 年龄：" + this.age;
    }
}
