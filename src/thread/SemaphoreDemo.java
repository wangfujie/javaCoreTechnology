package thread;

import java.util.concurrent.Semaphore;

/**
 * SemaphoreDemo
 * 适用场景：五个窗口买票，可以同时服务五个人
 *
 * @author wangfj
 * @date 2019-09-08 21:46
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(5);
        while (true){
            new Thread(() -> {
                try {
                    semaphore.acquire();//占用售票线程
                    System.out.println(Thread.currentThread().getName() + "-- 开始买票");
                    Thread.sleep(2000);//睡两秒，模拟买票过程
                    System.out.println(Thread.currentThread().getName() + "-- 购票成功");
                    semaphore.release();//释放售票线程
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
