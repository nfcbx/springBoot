package nettyTest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class NettyEncoder extends MessageToByteEncoder<Object> {


    @Override
    protected void encode(ChannelHandlerContext context, Object in, ByteBuf out) throws Exception {
        out.writeBytes(new byte[]{});
    }
}
