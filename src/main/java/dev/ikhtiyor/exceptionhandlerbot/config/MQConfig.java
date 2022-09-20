package dev.ikhtiyor.exceptionhandlerbot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MQConfig {

    @Value("${spring.rabbitmq.exception-handler.queues.bot-queues.name}")
    private String queueName;

    @Value("${spring.rabbitmq.exception-handler.queues.bot-queues.durable}")
    private boolean queueDurable;

    @Value("${spring.rabbitmq.exception-handler.queues.bot-queues.routing-key}")
    private String queueRoutingKey;

    @Bean
    public Queue exceptionHandlerQueue() {
        return new Queue(queueName, queueDurable);
    }

    @Bean
    public Binding queueBinding(DirectExchange defaultDirectExchange) {
        return BindingBuilder.bind(exceptionHandlerQueue()).to(defaultDirectExchange).with(queueRoutingKey);
    }
}
