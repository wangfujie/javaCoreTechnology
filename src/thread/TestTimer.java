package thread;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 任务调度
 *
 * @author wangfj
 * @datetime 2019-08-12 20:55
 */
public class TestTimer {

    public static void main(String[] args) {
        Timer timer = new Timer();

        //1秒后开始执行，每隔200毫秒执行一次
//        timer.schedule(new MyTask(), 1000, 200);
        //定时调度任务，开始时间2019-08-12 21:00:00 每隔1秒执行
        Calendar calendar = new GregorianCalendar(2019,7,12,21,0,0);
        System.out.println(calendar.getTime());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("定时任务执行成功=====================");
            }
        }, calendar.getTime(), 200);
    }

}

class MyTask extends TimerTask {

    @Override
    public void run() {
        System.out.println("定时任务执行成功=====================");
    }
}