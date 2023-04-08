package Server_Bootstrap;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // Send data to server
        String msg = "Hello, server!";
        byte[] data = msg.getBytes();
        ByteBuf buf = ctx.alloc().buffer(data.length);
        buf.writeBytes(data);
        ctx.writeAndFlush(buf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        try {
            byte[] data = new byte[buf.readableBytes()];
            buf.readBytes(data);
            // Do something with the received data
            System.out.println("Client received: " + new String(data));
        } finally {
            buf.release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Handle exception
        cause.printStackTrace();
        ctx.close();
    }

}
