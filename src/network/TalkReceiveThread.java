package network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *  UDP交流，接收端线程
 * @author wangfj
 */
public class TalkReceiveThread implements Runnable {

    private DatagramSocket datagramSocket;
    private String msgFrom;

    TalkReceiveThread(int port, String msgFrom){
        this.msgFrom = msgFrom;
        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                byte[] data = new byte[1024*2];
                DatagramPacket packet = new DatagramPacket(data, 0, data.length);
                datagramSocket.receive(packet);
                String msg = new String(packet.getData());
                System.out.println(this.msgFrom + "：" + msg);
                if ("bye".equals(msg)){
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //关闭资源
        datagramSocket.close();
    }

}
