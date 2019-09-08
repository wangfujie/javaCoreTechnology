package thread;

import java.util.Date;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier实例：（翻译--循环屏障）
 *
 * 适用场景：CyclicBarrier是让多个线程相互等待某一个事件发生，然后同时唤醒。
 *
 * 实现原理：加计数方式，
 * 使用await方法让线程等待，等待线程数+1，直到等待线程数达到指定值时，释放所有等待线程
 * 可以重复利用
 *
 *
 * @author wangfj
 * @date 2019-09-08 14:49
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        int totalThread = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(totalThread);
        for (int i = 0; i < 20; i++) {
            int threadNum = i + 1;
            String threadName = "thread" + threadNum;

            new Thread(() -> {
                System.out.println(String.format("%s\t%s\t%s", new Date(), threadName, "started"));
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("%s\t%s\t%s", new Date(), threadName, "ended"));
                if (threadNum%totalThread == 0){
                    cyclicBarrier.reset();
                }
            }).start();
        }
    }
}
