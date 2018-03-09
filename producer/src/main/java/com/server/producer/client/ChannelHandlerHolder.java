package com.server.producer.client;

import io.netty.channel.ChannelHandler;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/8
 */
public interface ChannelHandlerHolder {

    ChannelHandler[] handlers();

}
