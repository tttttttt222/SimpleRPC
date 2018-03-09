package com.server.producer.common;

import java.util.List;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/2
 */
public class MessageBody {

    private String sender;

    private List<String> receiver;

    private String msg;

    public MessageBody() {
    }

    public MessageBody(String sender, List<String> receiver, String msg) {
        this.sender = sender;
        this.receiver = receiver;
        this.msg = msg;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<String> receiver) {
        this.receiver = receiver;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MessageBody{" +
                "sender='" + sender + '\'' +
                ", receiver=" + receiver +
                ", msg='" + msg + '\'' +
                '}';
    }
}
