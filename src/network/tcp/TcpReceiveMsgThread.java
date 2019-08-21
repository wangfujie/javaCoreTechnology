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

    @Override
    public void run() {
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
