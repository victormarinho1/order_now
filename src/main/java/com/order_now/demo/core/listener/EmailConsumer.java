package com.order_now.demo.core.listener;

import com.order_now.demo.core.ApplicationUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;



import static com.order_now.demo.core.config.RabbitMqConfig.EMAIL_SENT_QUEUE;

@Component
public class EmailConsumer {

    private final Logger logger = LoggerFactory.getLogger(EmailSentEventDTO.class);

    private final ApplicationUserService applicationUserService;

    public EmailConsumer(ApplicationUserService authenticationService){
        this.applicationUserService = authenticationService;
    }

    @RabbitListener(queues = EMAIL_SENT_QUEUE)
    public void receiveEmail(Message<EmailSentEventDTO> message){
        logger.info("Message consumed: {}",message);
        applicationUserService.resetPassword(message.getPayload());
    }
}