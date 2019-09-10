package nio;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * socket客户端
 *
 * @author wangfj
 * @datetime 2019-09-10 22:23
 */
public class ClientDemo {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8088);
        Scanner scanner = new Scanner(System.in);
        while (true){
            String msg = scanner.next();
            socket.getOutputStream().write(msg.getBytes());
        }
    }
}
