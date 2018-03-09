package com.server.producer.common;


import com.server.producer.utils.JsonUtils;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/2
 */
public class MessageProtocol{

    private  int length;

    private String body;

    public MessageProtocol(int length, String body) {
        this.length = length;
        this.body = body;
    }

    public MessageProtocol() {
    }

    public MessageProtocol(MessageBody msgbody) {
        this.body = JsonUtils.parseJsontoString(msgbody);
        this.length =body.getBytes().length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
