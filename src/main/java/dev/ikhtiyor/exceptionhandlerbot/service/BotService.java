package dev.ikhtiyor.exceptionhandlerbot.service;

import dev.ikhtiyor.exceptionhandlerbot.payload.MessageDTO;

public interface BotService {

    void sendMessageToTelegramGroup(MessageDTO messageDTO);

}
