package com.server.producer.server;

import com.server.producer.common.ChannelMap;
import com.server.producer.common.MessageBody;
import com.server.producer.common.MessageProtocol;
import com.server.producer.utils.JsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/2
 */
public class ChannelSaveHandler extends SimpleChannelInboundHandler<MessageProtocol>{

    private boolean  isChannelActive = false;

    private String rid;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        isChannelActive = true;
        super.channelActive(ctx);
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol msg) throws Exception {
           //储存新的channel
        if(isChannelActive){
            MessageBody messageBody = JsonUtils.parseStringtoJson(msg.getBody(), MessageBody.class);
            ChannelMap.getInstance().getChmap().put(messageBody.getSender(),channelHandlerContext);
            rid=messageBody.getSender();
            isChannelActive=false;
        }
        channelHandlerContext.fireChannelRead(msg);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        ChannelMap.getInstance().getChmap().remove(rid);
    }


}
