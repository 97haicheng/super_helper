package com.chao.helper.amqp;

import org.springframework.amqp.core.Message;


public interface MQListener {
    void listen(Message message);
}
