package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

/**
 *  UDP交流，发送端线程
 * @author wangfj
 */
public class TalkSendThread implements Runnable {
    private DatagramSocket datagramSocket;
    private BufferedReader reader;
    private String sendHost;
    private int sendPort;

    TalkSendThread(int port, String sendHost, int sendPort){
        this.sendHost = sendHost;
        this.sendPort = sendPort;
        try {
            datagramSocket = new DatagramSocket(port);
            reader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true){
            try {
                String data = reader.readLine();
                byte[] dataByte = data.getBytes();
                DatagramPacket packet = new DatagramPacket(dataByte, 0, dataByte.length, new InetSocketAddress(this.sendHost, this.sendPort));
                datagramSocket.send(packet);
                if ("bye".equals(data)){
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
