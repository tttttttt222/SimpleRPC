package com.server.producer.client.comsumer;

import com.server.producer.common.MessageBody;

/**
 * 项目名称:producer
 * 描述:
 * 创建人:ryw
 * 创建时间:2017/11/10
 */
public interface OnDataCommingListener {

    void dataComming(MessageBody body);

}
