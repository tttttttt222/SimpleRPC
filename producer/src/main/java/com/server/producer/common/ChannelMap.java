package com.server.producer.common;

import io.netty.channel.ChannelHandlerContext;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/3
 */
public class ChannelMap  {

    private static  ChannelMap channelMap;

    private static Map<String,ChannelHandlerContext> chmap = new ConcurrentHashMap<String, ChannelHandlerContext>();

    private  ChannelMap() { }

    public static ChannelMap getInstance() {
        synchronized (ChannelMap.class){
            if (channelMap == null) {
                channelMap = new ChannelMap();
            }
        }
        return channelMap;
    }

    public static Map<String, ChannelHandlerContext> getChmap() {
        return chmap;
    }

    public static void setChmap(Map<String, ChannelHandlerContext> chmap) {
        ChannelMap.chmap = chmap;
    }
}
