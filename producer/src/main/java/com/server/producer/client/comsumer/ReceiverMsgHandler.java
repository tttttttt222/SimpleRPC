package com.server.producer.client.comsumer;

import com.server.producer.common.Commfield;
import com.server.producer.common.MessageBody;
import com.server.producer.common.MessageProtocol;
import com.server.producer.utils.JsonUtils;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/10
 */
@ChannelHandler.Sharable
public class ReceiverMsgHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    private OnDataCommingListener onDataCommingListener;

    private String sender;

    public ReceiverMsgHandler(String sender, OnDataCommingListener onDataCommingListener) {
        this.sender = sender;
        this.onDataCommingListener = onDataCommingListener;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ArrayList<String> receiver = new ArrayList<String>();
        receiver.add(Commfield.comsummerReceiver);
        MessageBody messageBody = new MessageBody(sender, receiver, "消费者包");
        MessageProtocol messageProtocol = new MessageProtocol(messageBody);
        ctx.writeAndFlush(messageProtocol);
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol messageProtocol) throws Exception {
        MessageBody messageBody = JsonUtils.parseStringtoJson(messageProtocol.getBody(), MessageBody.class);
        onDataCommingListener.dataComming(messageBody);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        super.exceptionCaught(ctx, cause);
    }

}
