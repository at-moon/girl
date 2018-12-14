package com.atmoon.handle;

import com.atmoon.pojo.Girl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQlReceiver {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQlReceiver.class);

    @RabbitListener(queues = "hello")
    public void receiveGirl(Girl girl) {
        logger.info("Receiver : " + girl);
    }
}
