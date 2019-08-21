package network.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 聊天室，客户端
 *
 * @author wangfj
 */
public class ChatRoomClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8088);
        System.out.println("客户端---------" + socket);
        TcpSendMsgThread sendMsgThread = new TcpSendMsgThread(socket);
        TcpReceiveMsgThread receiveMsgThread = new TcpReceiveMsgThread(socket);

        new Thread(sendMsgThread, "张三").start();
        new Thread(receiveMsgThread, "张三").start();
    }
}
