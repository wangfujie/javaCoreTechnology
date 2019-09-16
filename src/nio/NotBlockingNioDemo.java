package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * 非阻塞的NIO，服务端
 *
 * @author wangfj
 * @datetime 2019-09-16 22:02
 */
public class NotBlockingNioDemo {

    public static void main(String[] args) throws IOException {
        //1、获取服务端通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        //2、绑定连接
        ssChannel.bind(new InetSocketAddress(8088));
        //3、切换非阻塞模式
        ssChannel.configureBlocking(false);
        //4、获取选择器
        Selector selector = Selector.open();
        //5、将通道注册到选择器上，并且指定“监听接收事件”
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6、轮询式的获取选择器上已经准备就绪的事件
        while (selector.select() > 0){
            //7、获取当前选择器中所注册的“选择键（已就绪的监听事件）”
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while (it.hasNext()){
                //8、获取准备就绪的事件
                SelectionKey sk = it.next();
                //9、判断具体是什么事件准备就绪
                if (sk.isAcceptable()){
                    //10、若是“接收就绪”，获取客户端连接
                    SocketChannel sChannel = ssChannel.accept();
                    //11、切换客户端连接socket为非阻塞模式
                    sChannel.configureBlocking(false);
                    //12、将该通道注册到选择器上，指定“读事件”
                    sChannel.register(selector, SelectionKey.OP_READ);
                }else if (sk.isReadable()){
                    //13、获取当前选择器上“读就绪”状态的通道
                    SocketChannel sChannel = (SocketChannel) sk.channel();
                    //14、读取数据
                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    while (sChannel.read(buf) > 0){
                        buf.flip();
                        System.out.println(new String(buf.array()));
                        buf.clear();
                    }
                }
                //15、取消选择建SelectionKey
                it.remove();
            }
        }

    }
}
