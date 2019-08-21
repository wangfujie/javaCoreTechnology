package network.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 聊天室，服务端
 *
 * @author wangfj
 */
public class ChatRoomServer {

    public static void main(String[] args) throws IOException {
        boolean flag = true;
        ServerSocket clientSocket = new ServerSocket(8088);
        while (flag){
            Socket socket = clientSocket.accept();
            //开启管道线程
            new Thread(new ChannelThread(socket)).start();
        }
    }
}
