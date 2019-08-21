package network.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 基于TCP接收消息线程
 *
 * @author wangfj
 */
public class TcpReceiveMsgThread implements Runnable {
    private Socket socket;
    private DataInputStream receiveStream;

    public TcpReceiveMsgThread(Socket socket){
        try {
            this.socket = socket;
            receiveStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String message;
        while (!socket.isClosed()){
            try {
                message = receiveStream.readUTF();
                System.out.println(message);
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
            receiveStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
