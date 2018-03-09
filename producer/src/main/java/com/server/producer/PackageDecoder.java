package com.server.producer;

import com.server.producer.common.MessageBody;
import com.server.producer.common.MessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.util.List;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/2
 */
@ChannelHandler.Sharable
public class PackageDecoder extends ChannelInboundHandlerAdapter {



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf) msg;

            MessageProtocol messageProtocol = new MessageProtocol();

            int length = byteBuf.readInt();
            if (byteBuf.readableBytes() < length) {
                  return;
//                throw new Exception("报文长度小于所传长度");
            }

            byte[] req = new byte[length];
            byteBuf.readBytes(req);
            String body = new String(req, "UTF-8");

            messageProtocol.setLength(length);
            messageProtocol.setBody(body);
            ctx.fireChannelRead(messageProtocol);
        }else{
            throw  new Exception("非法报文");
        }


    }


    //    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
//        MessageProtocol messageProtocol = new MessageProtocol();
//
//        int length = byteBuf.readInt();
//        if (byteBuf.readableBytes() < length) {
//            throw new Exception("非法报文");
//        }
//
//        byte[] req = new byte[length];
//        byteBuf.readBytes(req);
//        String body = new String(req, "UTF-8");
//
//        messageProtocol.setLength(length);
//        messageProtocol.setBody(body);
//
//        channelHandlerContext.fireChannelRead(messageProtocol);
//    }

}
