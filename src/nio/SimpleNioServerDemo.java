package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * 简单的nio服务端，NIO非阻塞概念（原理）
 *
 * 轮询效率低：NIO将轮询交给操作系统做（将socket注册给操作系统函数）
 * windows系统是select函数，linux系统epoll函数
 *
 * @author wangfj
 * @datetime 2019-09-10 22:04
 */
public class SimpleNioServerDemo {
    static List<SocketChannel> list = new ArrayList<>();
    static ByteBuffer buffer = ByteBuffer.allocate(20);

    public static void main(String[] args) throws IOException {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(8088));
        //设置服务非阻塞接收连接
        socketChannel.configureBlocking(false);
        while (true){
            //轮询接收数据
            for (SocketChannel channel : list) {
                //设置非组赛
                channel.configureBlocking(false);
                int r = channel.read(buffer);
                if (r > 0){
                    //切换模式，写->读
                    buffer.flip();
                    //读取数据到字节数组中
                    byte[] msgByteArray = new byte[buffer.limit()];
                    buffer.get(msgByteArray);
                    System.out.println(new String(msgByteArray));
                    System.out.println(Charset.forName("utf-8").decode(buffer));
                    buffer.clear();
                }
            }
            //接收连接
            SocketChannel socket = socketChannel.accept();
            if (socket != null){
                System.out.println("有人连接上来了-------------");
                list.add(socket);
            }else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("等待连接");
            }
        }
    }
}
