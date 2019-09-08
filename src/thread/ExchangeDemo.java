package thread;

import java.util.concurrent.Exchanger;

/**
 * Exchange用于线程间协作工具
 *
 * @author wangfj
 * @date 2019-09-08 21:56
 */
public class ExchangeDemo {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger();
        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "开始运行线程0");
            try {
                Thread.sleep(1000);
                String data = exchanger.exchange("线程0的数据");
                System.out.println(threadName + "得到数据:" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + "开始运行线程1");
            try {
                String data = exchanger.exchange("线程1的数据");
                System.out.println(threadName + "得到数据:" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
