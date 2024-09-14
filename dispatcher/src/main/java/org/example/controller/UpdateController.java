package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.UpdateProducer;
import org.example.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Component
public class UpdateController {
    private MessageUtils messageUtils;
    private UpdateProducer messageProducer;
    private TelegramController telegramController;
    @Value("${doc.message}")
    private String docMessage;
    @Value("${photo.message}")
    private String photoMessage;
    @Value("${text.message}")
    private String textMessage;
    @Value("${answer.message}")
    private String answerMessage;


    public UpdateController(MessageUtils messageUtils, UpdateProducer messageProducer,TelegramController telegramController) {
        this.messageUtils = messageUtils;
        this.messageProducer = messageProducer;
        this.telegramController = telegramController;
    }

    public void processUpdate(Update update) {
        if (update == null) {
            log.error("update is null");
            return;
        }

        if (update.getMessage() == null) {
            log.error("unsupported message type");
            return;
        }

        distributeMessageByType(update);
    }

    private void distributeMessageByType(Update update) {
        if (update.getMessage().hasText()) {
            processTextMessage(update);
        } else if (update.getMessage().hasDocument()) {
            processDocumentMessage(update);
        } else if (update.getMessage().hasPhoto()) {
            processPhotoMessage(update);
        }
        setUnsupportedMessage(update);
    }

    private void setFileIsReceivedView(Update update) {
        System.out.println(update.getMessage().getText());
        SendMessage sendMessage = new SendMessage(update.getMessage().getChatId().toString(),"Файл получен! Обрабатывается...");
        telegramController.sendAnswerMessage(sendMessage);
        log.debug("Got message", update.getMessage());
    }

    public void setView(SendMessage sendMessage){
        telegramController.sendAnswerMessage(sendMessage);
    }

    private void processTextMessage(Update update) {
        messageProducer.produce(textMessage, update);
        setFileIsReceivedView(update);
    }

    private void processDocumentMessage(Update update) {
        messageProducer.produce(docMessage, update);
        setFileIsReceivedView(update);
    }

    private void processPhotoMessage(Update update) {
        messageProducer.produce(photoMessage, update);
        setFileIsReceivedView(update);
    }

    private void setUnsupportedMessage(Update update) {
        messageUtils.generateSendMessageWithText(update, "unsupportedMessage");
        log.error("{}unsupportedMessage", update);
    }

}
