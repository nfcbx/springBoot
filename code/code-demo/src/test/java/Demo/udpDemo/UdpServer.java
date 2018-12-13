package Demo.udpDemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by ZSX on 2018/6/7.
 *
 * @author ZSX
 */
public class UdpServer {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket=new DatagramSocket(8088);
        while(true){
            DatagramPacket packet=new DatagramPacket(new byte[512], 512);
            datagramSocket.receive(packet);
            String msg=new String(packet.getData(), 0, packet.getLength());
            System.out.println(packet.getAddress()+"/"+packet.getPort()+":"+msg);
            packet.setData("I am server!!!".getBytes());
            datagramSocket.send(packet);
        }
    }
}
