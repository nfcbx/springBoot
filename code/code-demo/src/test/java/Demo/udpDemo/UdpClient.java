package Demo.udpDemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by ZSX on 2018/6/7.
 *
 * @author ZSX
 */
public class UdpClient {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DatagramSocket datagramSocket=new DatagramSocket();
        InetAddress address= InetAddress.getByName("localhost");
        String msg="";
        while((msg=reader.readLine())!=""){
            //发送数据
            byte[] buffer=msg.getBytes();
            DatagramPacket packet=new DatagramPacket(buffer, buffer.length, address, 8088);
            datagramSocket.send(packet);
            //接收数据
            DatagramPacket inputPacket = new DatagramPacket(new byte[512], 512);
            datagramSocket.receive(inputPacket);
            System.out.println(new String(inputPacket.getData(), 0 , inputPacket.getLength()));
            datagramSocket.close();
        }

    }
}
