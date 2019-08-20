package thread;

/**
 * volatile用于保证数据的同步，也就是可见性
 *
 * @author wangfj
 * @datetime 2019-08-20 20:06
 */
public class VolatileTest {

    private /*volatile*/ static int num = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (num == 0){
                //如果没用volatile修饰num，这里就会进入死循环，使得后面的num = 1没有空写入主存
            }
        }).start();

        Thread.sleep(1000);

        num = 1;
        System.out.println("修改了num的值");
    }
}
