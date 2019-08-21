package network.tcp;

import java.io.Closeable;
import java.io.IOException;

/**
 * IO工具类
 *
 * @author wangfj
 * @datetime 2019-08-21 21:26
 */
public class IoUtils {

    /**
     * 释放资源
     * @param targets
     */
    public static void release(Closeable... targets){
        for (Closeable target : targets){
            if (target != null){
                try {
                    target.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
