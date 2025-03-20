package com.order_now.demo.core.config;

import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    public static final String EMAIL_SENT_QUEUE = "email-reset-password-sent";
    public static final String EMAIL_SENT_REGISTER_QUEUE = "email-register-user-sent";

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Declarable emailSentQueue(){
        return new Queue(EMAIL_SENT_QUEUE);
    }

    @Bean
    public Declarable emailRegisterUserSentQueue(){
        return new Queue(EMAIL_SENT_REGISTER_QUEUE);
    }
}