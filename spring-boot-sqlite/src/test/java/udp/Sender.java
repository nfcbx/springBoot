package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 客户端
 * Created by highness on 2018/1/10 0010.
 */
public class Sender {

    public static void main(String[] args) {
        String msg = "Hello, World";
        byte[] buf = msg.getBytes();
        try {
            InetAddress address = InetAddress.getByName("169.254.80.80");  //服务器地址
            int port = 8080;  //服务器的端口号
            //创建发送方的数据报信息
            DatagramPacket dataGramPacket = new DatagramPacket(buf, buf.length, address, port);

            DatagramSocket socket = new DatagramSocket();  //创建套接字
            socket.send(dataGramPacket);  //通过套接字发送数据

            //接收服务器反馈数据
            byte[] backbuf = new byte[1024];
            DatagramPacket backPacket = new DatagramPacket(backbuf, backbuf.length);
            socket.receive(backPacket);  //接收返回数据
            String backMsg = new String(backbuf, 0, backPacket.getLength());
            System.out.println("服务器返回的数据为:" + backMsg);

            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
