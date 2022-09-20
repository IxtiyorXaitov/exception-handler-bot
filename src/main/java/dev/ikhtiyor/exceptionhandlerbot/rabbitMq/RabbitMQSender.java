package dev.ikhtiyor.exceptionhandlerbot.rabbitMq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQSender {
    private final RabbitTemplate rabbitTemplate;

    private void send(String exchange, String routingKey, Object message) {
        log.info("Start queueing: Exchange = {}, RoutingKey = {}, Message = {}", exchange, routingKey, message.toString());

        rabbitTemplate.convertAndSend(exchange, routingKey, message);

        log.info("End queueing: Exchange = {}, RoutingKey = {}, Message = {}", exchange, routingKey, message.toString());
    }

}
