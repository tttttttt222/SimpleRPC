package com.server.producer.server;

import com.server.producer.common.ChannelMap;
import com.server.producer.common.Commfield;
import com.server.producer.common.MessageBody;
import com.server.producer.common.MessageProtocol;
import com.server.producer.utils.JsonUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.List;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/3
 */
public class ProductHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageProtocol msg) throws Exception {
        MessageBody messageBody = JsonUtils.parseStringtoJson(msg.getBody(), MessageBody.class);
        List<String> receiver = messageBody.getReceiver();
        System.out.println("接收到报文:"+messageBody.getMsg());
        for (String r : receiver) {
            if(r.equals(Commfield.comsummerReceiver)){
                break;
            }
            if (ChannelMap.getInstance().getChmap().containsKey(r)) {
                ChannelMap.getInstance().getChmap().get(r).writeAndFlush(msg);
            }
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
         System.out.println(cause);
         ctx.close();
    }

}
