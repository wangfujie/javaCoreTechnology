package network;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

/**
 *
 * @author wangfj
 */
public class MyNetTest {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        //接收端线程
        new Thread(() -> {
            //1. 使用DatagramSocket指定端口，创建接收端
            DatagramSocket ds = null;
            try {
                ds = new DatagramSocket(8089);
                //2. 准备容器，封装成DatagramPacket包裹
                byte[] data = new byte[1024*60];
                DatagramPacket dp = new DatagramPacket(data, 0, data.length-1);
                //3. 阻塞式接收包裹receive(DatagramPacket dp)
                ds.receive(dp);
                //4. 获取数据bety[] getData()
//                System.out.println("接收到的数据："+ new String(dp.getData()));
                readTypeByte(dp.getData());
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //5. 释放资源
                ds.close();
            }
        }).start();

        //发送端线程
        new Thread(() -> {
            //1. 使用DatagramSocket指定端口，创建发送端
            DatagramSocket ds = null;
            try {
                ds = new DatagramSocket(8088);
                //2. 准备数据，一定转成字节数组
                byte[] data = writeTypeByte();
                //3. 封装成DatagramPacket包裹，需要指定目的地(InetSocketAddress)
                DatagramPacket dp = new DatagramPacket(data, 0, data.length, new InetSocketAddress("localhost", 8089));
                //4. 发送包裹send(DatagramPacket dp)
                ds.send(dp);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                //5. 释放资源
                ds.close();
            }
        }).start();

    }

    /**
     * IO流，基本类型转byte数组
     * @return
     * @throws IOException
     */
    public static byte[] writeTypeByte() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeUTF("写入字符");
        dos.writeInt(11);
        dos.writeBoolean(true);
        dos.writeChar('a');

        dos.flush();
        return baos.toByteArray();
    }

    /**
     * IO流读取byte数组，基本类型
     * @return
     * @throws IOException
     */
    public static void readTypeByte(byte[] datas) throws IOException {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(datas));
        String utf = dis.readUTF();
        int i = dis.readInt();
        boolean bool = dis.readBoolean();
        char ch = dis.readChar();

        System.out.println("String:" + utf);
        System.out.println("int:" + i);
        System.out.println("boolean:" + bool);
        System.out.println("char:" + ch);
    }

    //发送对象需要序列化和反序列化（一切都是转成字节数组发送接收）
    //发送文件图片需要使用io流转成byte数组发送和接收，但是不能传太大一般不超过64kb
}
