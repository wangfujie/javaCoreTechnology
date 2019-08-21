package network.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 基于TCP发送消息线程
 *
 * @author wangfj
 */
public class TcpSendMsgThread implements Runnable {
    private Socket socket;
    private DataOutputStream sendStream;
    private BufferedReader reader;

    public TcpSendMsgThread(Socket socket, String name){
        try {
            this.socket = socket;
            sendStream = new DataOutputStream(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(System.in));
            //发送客户端名称
            sendStream.writeUTF(name);
        } catch (IOException e) {
            //关闭资源
            close();
        }
    }

    /**
     * 接受控制台输入，并发送消息
     */
    public void sendMsg(String message){
        try {
            sendStream.writeUTF(message);
            sendStream.flush();
        } catch (IOException e) {
            //关闭资源
            close();
        }
    }

    @Override
    public void run() {
        while (!socket.isClosed()){
            try {
                String message = reader.readLine();
                if ("exit".equals(message)){
                    break;
                }
                sendMsg(message);
            } catch (IOException e) {
                //关闭资源
                close();
            }
        }
        //关闭资源
        close();
    }

    /**
     * 关闭资源
     */
    private void close(){
        IoUtils.release(reader,sendStream);
    }
}
