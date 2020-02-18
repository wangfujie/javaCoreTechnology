package network.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 基于TCP接收消息线程
 *
 * @author wangfj
 */
public class TcpReceiveMsgThread implements Runnable {
    private Socket socket;
    private DataInputStream receiveStream;
    private DataOutputStream sendStream;

    public TcpReceiveMsgThread(Socket socket){
        try {
            this.socket = socket;
            receiveStream = new DataInputStream(socket.getInputStream());
            sendStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

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
     * 发送消息
     * @param message
     */
    public void sendMsg(String message){
        try {
            sendStream.write(message.getBytes());
            sendStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
            //关闭资源
            close();
        }
    }

    @Override
    public void run() {
        if (!socket.isClosed()){
            System.out.println("发送了消息");
            sendMsg("hello server");
        }
        while (!socket.isClosed()){
            System.out.println(receiveMsg());
        }
        //关闭资源
        close();
    }

    /**
     * 关闭资源
     */
    private void close(){
        IoUtils.release(receiveStream,socket);
    }
}
