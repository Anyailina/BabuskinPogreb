package org.example.service.impl;

import org.example.controller.UpdateController;
import org.example.service.AnswerConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class AnswerConsumerImpl implements AnswerConsumer {
    private final UpdateController updateController;

    public AnswerConsumerImpl(UpdateController updateController) {
        this.updateController = updateController;
    }

    @Override
    @KafkaListener(topics = "answer_message",groupId = "node")
    public void consume(SendMessage message) {
        updateController.setView(message);
    }
}
