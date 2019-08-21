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
    private boolean isOnline = true;

    public TcpSendMsgThread(Socket socket){
        try {
            this.socket = socket;
            sendStream = new DataOutputStream(socket.getOutputStream());
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message;
        while (isOnline){
            try {
                message = reader.readLine();
                if ("exit".equals(message)) {
                    isOnline = false;
                    sendStream.writeUTF(Thread.currentThread().getName() + "下线了");
                    break;
                }
                sendStream.writeUTF(Thread.currentThread().getName() + "说:" + message);
            } catch (IOException e) {
                e.printStackTrace();
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
            reader.close();
            sendStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
