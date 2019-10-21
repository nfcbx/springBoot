package nettyTest.echoServer;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage:  " + EchoServer.class.getSimpleName() + "  <port>");
            return;
        }
//        设置端口号（如果参数不正确则抛出异常）
        int port = Integer.parseInt(args[0]);
//        调用start()方法
        new EchoServer(port).start();
    }


    public void start() throws Exception {
        EchoServerHandler serverHandler = new EchoServerHandler();
//      创建 EventLoopGroup
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
//      创建 ServerBootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(group)
//                指定所使用的NIO传输Channel
                    .channel(NioServerSocketChannel.class)
//                使用指定的端口
                    .localAddress(new InetSocketAddress(port))
//                添加一个EchoServerHandler到子Channel的ChannelPipeline
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
// EchoServerHandler被标注为@Shareable，所以可以总是使用同样的实例
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
//        异步的绑定服务器，调用sync()方法阻塞等待直到绑定完成
            ChannelFuture cf = bootstrap.bind().sync();

//        获取Channel的CloseFuture,并且阻塞当前线程直到它完成
            cf.channel().closeFuture().sync();
        } finally {
//            关闭EventLoopGroup，释放所有的资源
            group.shutdownGracefully().sync();
        }

    }

}
