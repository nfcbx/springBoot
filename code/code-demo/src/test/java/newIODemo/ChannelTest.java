package newIODemo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by ZSX on 2018/11/13.
 *
 * @author ZSX
 */
public class ChannelTest {

    public static void main(String[] args) throws Exception {
        ServerSocketChannelTest();

    }

    public static void test1() throws Exception {

//        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("https://www.javadoop.com", 80));

        // 打开一个通道
        SocketChannel socketChannel = SocketChannel.open();
        // 发起连接
        socketChannel.connect(new InetSocketAddress("https://www.javadoop.com", 80));


    }

    public static void ServerSocketChannelTest() throws IOException {
        // 实例化
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 监听 8080 端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress(8080);
//        InetSocketAddress inetSocketAddress = new InetSocketAddress("10.37.251.63", 80);
        serverSocketChannel.socket().bind(inetSocketAddress);

        while (true) {
            // 一旦有一个 TCP 连接进来，就对应创建一个 SocketChannel 进行处理
            SocketChannel socketChannel = serverSocketChannel.accept();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            socketChannel.read(byteBuffer);

            byteBuffer.flip();

            byte[] array = byteBuffer.array();
            String trim = new String(array).trim();
            System.out.println(trim);
        }
    }
}
