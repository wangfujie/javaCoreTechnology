package network.tcp;

import java.io.IOException;
import java.net.Socket;

/**
 * 聊天室，客户端
 *
 * @author wangfj
 */
public class ChatRoomClient02 {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8088);
        new Thread(new TcpSendMsgThread(socket,"李四")).start();
        new Thread(new TcpReceiveMsgThread(socket)).start();
    }
}
