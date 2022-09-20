package dev.ikhtiyor.exceptionhandlerbot.service;

import dev.ikhtiyor.exceptionhandlerbot.payload.MessageDTO;
import dev.ikhtiyor.exceptionhandlerbot.payload.UserDTO;
import dev.ikhtiyor.exceptionhandlerbot.telegram.TelegramBot;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotServiceImpl implements BotService {

    private final TelegramBot telegramBot;

    @Value("${telegram.bot.group-id}")
    String groupChatId;

    @Override
    public void sendMessageToTelegramGroup(MessageDTO messageDTO) {

        UserDTO currentUser = messageDTO.getUser();

        String message = messageDTO.getMessage();

        StrBuilder str = new StrBuilder();

        str.append("<b>MESSAGE:</b> \n");
        str.append(message);
        str.append("\n");

        str.append("<b>DATE:</b> \n");
        str.append(new Date().toString());
        str.append("\n");

        str.append("<b>USER:</b> \n");
        str.append("First name: " + currentUser.getFirstName());
        str.append("\n");
        str.append("Last name: " + currentUser.getLastName());
        str.append("\n");
        str.append("Phone number: " + currentUser.getPhoneNumber());
        str.append("\n");

        str.append("ID: " + currentUser.getId());
        str.append("\n");

        str.append("<b>URL:</b> " + messageDTO.getUrl());
        str.append("\n");

        str.append("#" + messageDTO.getServiceName());

        String errorMessage = str.toString();

        sendMessageToGroup(errorMessage);
    }

    public void sendMessageToGroup(String textMessage) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(groupChatId);
        sendMessage.setText(textMessage);
        sendMessage.setParseMode(ParseMode.HTML);

        telegramBot.sendMessage(sendMessage);
    }

}
