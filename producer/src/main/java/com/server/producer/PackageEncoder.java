package com.server.producer;

import com.server.producer.common.MessageProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/2
 */
@ChannelHandler.Sharable
public class PackageEncoder extends MessageToByteEncoder<MessageProtocol> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol, ByteBuf byteBuf) throws Exception {
        byte[] body = messageProtocol.getBody().getBytes();
        byteBuf.writeInt(body.length);
        byteBuf.writeBytes(body);
    }

}
