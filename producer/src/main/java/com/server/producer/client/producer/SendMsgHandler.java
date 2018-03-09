package com.server.producer.client.producer;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/10
 */
@ChannelHandler.Sharable
public class SendMsgHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

    }

}
