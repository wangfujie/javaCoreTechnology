package network.tcp;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于TCP发送消息线程
 *
 * @author wangfj
 */
public class TcpMsgForwardThread implements Runnable {
    private static List<Socket> socketList = new ArrayList<>();
    private Socket theSocket;
    private DataInputStream receiveStream;
    private DataOutputStream sendStream;

    public TcpMsgForwardThread(Socket socket){
        try {
            this.theSocket = socket;
            System.out.println("有人连上了---------" + socket);
            socketList.add(socket);
            receiveStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message;
        while (!theSocket.isClosed()){
            try {
                //服务端接收消息
                message = receiveStream.readUTF();
                System.out.println("服务端收到消息："+ message);

                //服务端转发消息给其他人
                if (socketList != null){
                    String finalMessage = message;
                    socketList.forEach(socket -> {
                        try {
                            if (theSocket != socket){
                                sendStream = new DataOutputStream(socket.getOutputStream());
                                sendStream.writeUTF(finalMessage);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } catch (IOException e) {
//                e.printStackTrace();
                close();
                break;
            }
        }
        //关闭资源
        close();
    }

    /**
     * 关闭资源
     */
    private void close(){
        try {
            if (receiveStream != null){
                receiveStream.close();
            }
            if (theSocket != null){
                socketList.remove(theSocket);
                theSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
