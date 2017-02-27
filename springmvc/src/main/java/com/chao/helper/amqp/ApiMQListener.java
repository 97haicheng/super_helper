package com.chao.helper.amqp;

import com.chao.helper.MessageQueueKey;
import com.chao.helper.application.SpringContextListener;
import com.chao.helper.utils.SpringRegisterUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author by barry-acer on 2016/3/1.
 */
@Component
public class ApiMQListener implements MQListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue(durable = "true"),
                    exchange = @Exchange(value = "fanoutExchange", type = "fanout", durable = "true")
            ),
            admin = "rabbitAdmin"
    )
    public void listen(Message message) {

        MessageProperties mp = message.getMessageProperties();

        Map<String, Object> map = mp.getHeaders();

        if (MessageQueueKey.PROVIDER_REGISTER.equals(mp.getReceivedRoutingKey())) { //通道注册

            String supplierCode = (String) map.get("supplierCode");

            byte[] body = message.getBody();
            ApplicationContext ctx = SpringContextListener.getAppContext();

            if (body!= null && ctx != null) {
                logger.debug("api register provider, supplierCode:{}", supplierCode);
                SpringRegisterUtils.register(ctx, supplierCode, body);
            }
        }

    }
}
