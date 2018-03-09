package com.server.producer.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.util.Timeout;
import io.netty.util.Timer;
import io.netty.util.TimerTask;

import java.util.concurrent.TimeUnit;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/8
 */
@ChannelHandler.Sharable
public abstract class ConnectionWatchdog extends ChannelInboundHandlerAdapter implements TimerTask, ChannelHandlerHolder {

    private final Bootstrap bootstrap;
    private final Timer timer;

    private final int port;
    private final String host;

    private volatile boolean reconnect = true;

    // 尝试重置次数
    private int attempts;

    public ConnectionWatchdog(Bootstrap bootstrap, Timer timer, int port, String host, boolean reconnect) {
        this.bootstrap = bootstrap;
        this.timer = timer;
        this.port = port;
        this.host = host;
        this.reconnect = reconnect;
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("当前链路已经激活了，重连尝试次数重新置为0");
        attempts = 0;
        ctx.fireChannelActive();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("链接关闭");
        if (reconnect) {
            if (attempts < 12) {
                attempts++;
                int timeout = 2 << attempts;
                timer.newTimeout(this, timeout, TimeUnit.SECONDS);
            }
            ctx.fireChannelInactive();
        }
    }

    public abstract ChannelHandler[] handlers();


    public void run(Timeout timeout) throws Exception {

        ChannelFuture channelFuture;

        synchronized (bootstrap) {
            bootstrap.handler(new ChannelInitializer<Channel>() {
                protected void initChannel(Channel channel) throws Exception {
                    channel.pipeline().addLast(handlers());
                }
            });
        }

        channelFuture = bootstrap.connect(host, port);
        channelFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                boolean success = channelFuture.isSuccess();
                if(!success){
                       System.out.println("重连失败");
                       channelFuture.channel().pipeline().fireChannelInactive();
                }else{
                    System.out.println("重连成功");
                }
            }

        });

    }


}
