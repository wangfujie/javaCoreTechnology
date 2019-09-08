package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author wangfj
 */
public class ManyThreadTest {

    public static void main(String[] args) {
        //创建ThreadPoolExecutor实例的线程池
        ExecutorService threadPool = Executors.newCachedThreadPool();//返回的是
        threadPool.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "在newCachedThreadPool创建的线程池中运行");
        });
        //创建ThreadPoolExecutor实例的线程池，指定corePoolSize和maximumPoolSize参数的值
        ExecutorService threadPool2 = Executors.newFixedThreadPool(5);
        threadPool2.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "在newFixedThreadPool创建的线程池中运行");
        });
    }
}
