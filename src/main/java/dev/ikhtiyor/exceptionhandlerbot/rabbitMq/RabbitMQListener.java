package dev.ikhtiyor.exceptionhandlerbot.rabbitMq;

import dev.ikhtiyor.exceptionhandlerbot.payload.MessageDTO;
import dev.ikhtiyor.exceptionhandlerbot.service.BotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitMQListener {

    private final BotService botService;

    @RabbitListener(queues = "exceptionHandler.bot-queue")
    public void receivedMessage(MessageDTO messageDTO) {
        try {
            botService.sendMessageToTelegramGroup(messageDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
