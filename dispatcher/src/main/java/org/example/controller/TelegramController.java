package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Component
@Slf4j
public  class TelegramController extends TelegramLongPollingBot {
    @Value("${bot.name}")
    protected String name;
    @Value("${bot.token}")
    protected String token;

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return name ;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    public  void sendAnswerMessage(SendMessage sendMessage){
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(String.valueOf(e));
        }
    }


}
