import base.*;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 主函数入口类
 * @author wangfujie
 */
public class Main {

    public static strictfp void main(String[] args) throws Exception {
        //返回java堆大小，单位字节
        System.out.println("JAVA堆：" + Runtime.getRuntime().maxMemory());
        ReentrantLock lock = new ReentrantLock();
    }
}
