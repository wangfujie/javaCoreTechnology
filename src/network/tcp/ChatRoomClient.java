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
        Socket socket = new Socket("127.0.0.1", 10201);
//        new Thread(new TcpSendMsgThread(socket,"张三")).start();
        new Thread(new TcpReceiveMsgThread(socket)).start();
    }
}
