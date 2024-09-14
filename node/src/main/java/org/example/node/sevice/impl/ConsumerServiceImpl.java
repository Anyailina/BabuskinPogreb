package org.example.node.sevice.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.node.sevice.ServiceConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
public class ConsumerServiceImpl implements ServiceConsumer {
    private final ProducerServiceImpl producerService;

    public ConsumerServiceImpl(ProducerServiceImpl producerService) {
        this.producerService = producerService;
    }

    @Override
    @KafkaListener(topics = "text_message",groupId = "node")
    public void consumeTextMessage(String string) {
        System.out.println(123);
        log.debug("TextMessage Received");

//        String chatId = update.getMessage().getChatId().toString();
//        SendMessage sendMessage = new SendMessage(chatId,string);
//        producerService.producerAnswer(sendMessage);
    }

    @Override
    @KafkaListener(topics = "doc_message")
    public void consumeDocumentMessage(Update update) {
        log.debug("DocumentMessage Received");
    }

    @Override
    @KafkaListener(topics = "photo_message")
    public void consumePhotoMessage(Update update) {
        log.debug("PhotoMessage Received");
    }
}
