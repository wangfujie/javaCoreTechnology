package network;

/**
 *  在线交流，线程实现双向交流
 * @author wangfj
 */
public class UdpTalkClient {

    public static void main(String[] args) {
        //发送端线程
        new Thread(new TalkSendThread(8000,"localhost", 9001)).start();
        //接收端线程
        new Thread(new TalkReceiveThread(8001, "张三")).start();
    }
}

class peopleOne {

    public static void main(String[] args) {
        //发送端线程
        new Thread(new TalkSendThread(9000,"localhost", 8001)).start();
        //接收端线程
        new Thread(new TalkReceiveThread(9001,"李四")).start();
    }
}
