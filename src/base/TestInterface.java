package base;

/**
 * java8新特性：接口的默认方法和静态方法
 * @author wangfj
 */
public interface TestInterface {

    void test();

    /**
     * java8新特性：接口默认方法，实现该接口的类可以不实现该方法，默认继承该方法，必要时也可以重写该方法
     * 应用场景：当一个接口有多个实现类时，需要增加某个相同的方法，这是只需要在接口中增加默认方法，这样每个实现类则均拥有了该默认方法
     * 例如：Collection接口中的stream方法就是default方法
     */
    default void defaultMethod(){
        System.out.println("java8的接口默认方法");
    }

    /**
     * java8新特性：接口静态方法，用法同普通静态方法（TestInterface.staticMethod();），实现类不具有该方法
     * 应用场景：
     */
    static void staticMethod(){
        System.out.println("java8的接口静态方法");
    }
}
