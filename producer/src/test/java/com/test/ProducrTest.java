package com.test;

import com.server.producer.client.producer.ProducerClinetConnect;

import java.util.ArrayList;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/10
 */
public class ProducrTest {

    public static int count = 0;

    public static void main(String args[]) throws Exception {
        ProducerClinetConnect producerClinetConnect = new ProducerClinetConnect(8900, "127.0.0.1");
        producerClinetConnect.connect();
        ArrayList<String> receive = new ArrayList<String>();
        receive.add("RZF1000");
        receive.add("RZF1001");
        while(true){
            Thread.sleep(3000);
            String msg = "通知"+ count;
            producerClinetConnect.sendMessage("RZF3000",receive,msg);
            count++;
        }
    }
}
