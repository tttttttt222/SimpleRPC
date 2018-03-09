package com.server.producer.client.producer;

import com.server.producer.common.MessageBody;
import com.server.producer.common.MessageProtocol;
import io.netty.channel.ChannelFuture;

import java.util.List;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/10
 */
public class ProducerClinetConnect {

    private int port;


    private String host;

    private ChannelFuture channelFuture;



    public ProducerClinetConnect(int port,  String host) {
        this.port = port;
        this.host = host;
    }

    public void connect() throws Exception {
        channelFuture = new ProducerClient().connect(port, host);
    }

    public void sendMessage(String sender, List<String> receiver, String msg){
        MessageBody messageBody = new MessageBody(sender, receiver, msg);
        MessageProtocol messageProtocol = new MessageProtocol(messageBody);
        channelFuture.channel().writeAndFlush(messageProtocol);
    }


}
