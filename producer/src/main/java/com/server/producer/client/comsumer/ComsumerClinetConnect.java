package com.server.producer.client.comsumer;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/10
 */
public class ComsumerClinetConnect {

    private int port;

    private OnDataCommingListener onDataCommingListener;

    private String host;

    private String sender;


    public ComsumerClinetConnect(int port,  String host,String sender,OnDataCommingListener onDataCommingListener) {
        this.port = port;
        this.host = host;
        this.sender =sender;
        this.onDataCommingListener = onDataCommingListener;
    }

    public void connect() throws Exception {
         new ComsumerClient().connect(port,host,sender,onDataCommingListener);
    }


}
