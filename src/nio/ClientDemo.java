package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * socket客户端
 *
 * @author wangfj
 * @datetime 2019-09-10 22:23
 */
public class ClientDemo {

    public static void main(String[] args) throws IOException {
        //普通方式Socket
        socket();
        //通道方式Socket
//        socketChannel();
    }

    /**
     * 通道方式Socket
     */
    public static void socketChannel() throws IOException{
        SocketChannel socketChannel = SocketChannel.open (new InetSocketAddress(8088));
        Scanner scanner = new Scanner(System.in);
        while (true){
            String msg = scanner.next();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(msg.getBytes());
            buffer.flip();
            socketChannel.write(buffer);
        }
    }

    /**
     * 普通方式Socket
     */
    public static void socket() throws IOException{
        Socket socket = new Socket("localhost", 8088);
        Scanner scanner = new Scanner(System.in);
        while (true){
            String msg = scanner.next();
            socket.getOutputStream().write(msg.getBytes());
        }
    }
}
