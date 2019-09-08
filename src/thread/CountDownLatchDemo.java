package thread;

import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch实例：（翻译--倒计时锁）
 *
 * 适用场景：
 *  Java多线程编程中经常会碰到这样一种场景——
 *  某个线程需要等待一个或多个线程操作结束（或达到某种状态）才开始执行。
 *  比如开发一个并发测试工具时，主线程需要等到所有测试线程均执行完成再开始统计总共耗费的时间，
 *  此时可以通过CountDownLatch轻松实现。
 *
 * 实现原理：
 *  可以简单的看成一个计数器，初始设置计数器大小，
 *  每次调用countDown方法计数器减1（如果计数器的值大于1，将其减1，如果计数器等于1，将计数器致为0，并唤醒所有通过await方法等待的线程），
 *  而await方法会阻塞等待计数器变为0
 *
 *  参考:http://www.jasongj.com/java/thread_communication/
 * @author wangfj
 * @date 2019-09-08 14:33
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        int totalThread = 3;
        long start = System.currentTimeMillis();
        //创建计数器为3的CountDownLatch
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        for (int i = 0; i < totalThread; i++) {
            final String threadName = "thread" + i;
            new Thread(() -> {
                System.out.println(String.format("%s\t%s\t%s", new Date(), threadName, "started"));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("%s\t%s\t%s", new Date(), threadName, "end"));
                //使计数器减1
                countDownLatch.countDown();
            }).start();
        }
        //阻塞等待计数器变为0
        countDownLatch.await();
        long stop = System.currentTimeMillis();
        System.out.println(String.format("三个线程总耗时 : %sms", (stop - start)));
    }
}
