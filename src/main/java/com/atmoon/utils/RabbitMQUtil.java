package com.atmoon.utils;

import com.atmoon.pojo.Girl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQUtil {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQUtil.class);

    private final AmqpAdmin amqpAdmin;
    private final AmqpTemplate amqpTemplate;

    @Autowired
    public RabbitMQUtil(AmqpAdmin amqpAdmin, AmqpTemplate amqpTemplate) {
        this.amqpAdmin = amqpAdmin;
        this.amqpTemplate = amqpTemplate;
    }

    /**
     * 发送对象
     * @param girl
     */
    public void sendGirl(Girl girl) {
        logger.info("Sender : " + girl);
        this.amqpTemplate.convertAndSend("hello",girl);
    }
}
