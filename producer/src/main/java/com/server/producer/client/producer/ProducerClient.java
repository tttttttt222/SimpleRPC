package com.server.producer.client.producer;

import com.server.producer.PackageDecoder;
import com.server.producer.PackageEncoder;
import com.server.producer.client.ConnectionWatchdog;
import com.server.producer.client.ConnectorIdleStateTrigger;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.HashedWheelTimer;

import java.util.concurrent.TimeUnit;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/7
 */
public class ProducerClient {

    protected HashedWheelTimer timer = new HashedWheelTimer();

    private Bootstrap bootstrap;


    public ChannelFuture connect(int port, String host) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group).channel(NioSocketChannel.class);

        //断线重连
        final ConnectionWatchdog watchdog = new ConnectionWatchdog(bootstrap, timer, port, host, true) {

            public ChannelHandler[] handlers() {
                return new ChannelHandler[]{
                        this,
                        new IdleStateHandler(0,5,0, TimeUnit.SECONDS),
                        new ConnectorIdleStateTrigger(),
                        new PackageEncoder()
                };
            }
        };

        ChannelFuture channelFuture;
        try {
            synchronized (bootstrap) {
                bootstrap.handler(new ChannelInitializer<Channel>() {
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(watchdog.handlers());
                    }
                });
                channelFuture = bootstrap.connect(host,port);
            }

            channelFuture.sync();
            return channelFuture;
        } catch (Exception e) {
            throw new Exception("connects to fails", e);
        }

    }


/*    public static void main(String args[]) throws Exception {
        int port = 8900;
        new ProducerClient().connect(port, "127.0.0.1");
    }*/


}
