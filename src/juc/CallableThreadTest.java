package juc;

import java.util.concurrent.*;

/**
 *  Callable方式实现线程
 * @author wangfj
 */
public class CallableThreadTest implements Callable<String> {

    public static void main(String[] args) {
        CallableThreadTest ctt = new CallableThreadTest();
        FutureTask<String> ft = new FutureTask(ctt);

        new Thread(ft,"有返回值的线程").start();

        try {
            //get获取线程返回值，如果线程没执行完成，则等待执行完毕后返回
            System.out.println("打印线程返回值：" + ft.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String call() throws Exception {
        System.out.println("执行了线程----------");
        Thread.sleep(1000);
        System.out.println("线程执行完毕----------");
        return "return thread value";
    }
}
