package nettyTest.echoServer;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 定义一个响应传入消息的接口
 */
@ChannelHandler.Sharable // 标示一个ChannelHandler可以被多个Channel安全的共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    // 对于每个传入的消息都要调用
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("打印消息：   " + in.toString(CharsetUtil.UTF_8));

//        这里简单处理，把数据会写给发送者
        ctx.write(in);
//        super.channelRead(ctx, msg);
    }


    // 通知ChannelInboundHandler最后一次对channelRead()的调用是单前批量读取中的最后一条消息
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//        将未决消息冲刷到远程节点，并且关闭该Channel  todo
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);

//        super.channelReadComplete(ctx);
    }


    // 在读取操作期间，有异常抛出时会调用
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();

//        super.exceptionCaught(ctx, cause);
    }

}
