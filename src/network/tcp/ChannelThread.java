package network.tcp;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 管道线程
 *
 * @author wangfj
 */
public class ChannelThread implements Runnable {
    private static CopyOnWriteArrayList<Socket> socketList = new CopyOnWriteArrayList<>();
    private String theName;
    private Socket theSocket;
    private DataInputStream receiveStream;
    private DataOutputStream sendStream;

    public ChannelThread(Socket socket){
        try {
            this.theSocket = socket;
            socketList.add(socket);
            receiveStream = new DataInputStream(socket.getInputStream());
            theName = receiveStream.readUTF();
            System.out.println(theName + "连接上了聊天室");
            sendMsgOther(theName + "进入了聊天室");
        } catch (IOException e) {
            //关闭资源
            close();
        }
    }

    /**
     * 接收消息
     * @return
     */
    public String receiveMsg(){
        String msg = "";
        try {
            msg = receiveStream.readUTF();
        } catch (IOException e) {
            //关闭资源
            close();
        }
        return msg;
    }

    /**
     * 转发消息给其他人
     * @param message
     */
    public void sendMsgOther(String message){
        if (socketList != null){
            socketList.forEach(socket -> {
                try {
                    if (theSocket != socket){
                        sendStream = new DataOutputStream(socket.getOutputStream());
                        sendStream.writeUTF(message);
                        sendStream.flush();
                    }
                } catch (IOException e) {
                    //关闭资源
                    close();
                }
            });
        }
    }

    @Override
    public void run() {
        while (!theSocket.isClosed()){
            //服务端接收消息
            String message = receiveMsg();
            if ("".equals(message)){
                sendMsgOther(theName + "退出了聊天室");
                System.out.println("============================================");
                System.out.println(theName + "退出了聊天室");
                System.out.println("当前聊天室剩余人数：" + socketList.size());
                System.out.println("============================================");
                break;
            }
            //服务端转发消息给其他人
            sendMsgOther(theName + "说：" + message);
        }
        //关闭资源
        close();
    }

    /**
     * 关闭资源
     */
    private void close(){
        IoUtils.release(receiveStream,theSocket);
        socketList.remove(theSocket);
    }
}
