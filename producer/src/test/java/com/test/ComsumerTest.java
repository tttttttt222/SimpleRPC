package com.test;

import com.server.producer.client.comsumer.ComsumerClinetConnect;
import com.server.producer.client.comsumer.OnDataCommingListener;
import com.server.producer.common.MessageBody;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/10
 */
public class ComsumerTest {

   public static  void main(String args[]) throws Exception{
       ComsumerClinetConnect comsumerClinetConnect = new ComsumerClinetConnect(8900, "127.0.0.1", "RZF1001",new OnDataCommingListener() {
           public void dataComming(MessageBody body) {
               System.out.println("消费者接收到了消息:" + body.getMsg());
           }
       });

       comsumerClinetConnect.connect();
   }
}
