package org.example.node.sevice.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.node.sevice.ProducerService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
@Slf4j
public class ProducerServiceImpl implements ProducerService {
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public ProducerServiceImpl(KafkaTemplate<String,String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void producerAnswer(SendMessage sendMessage) {
        try {
            String message = objectMapper.writeValueAsString(sendMessage);
            kafkaTemplate.send("answer_message", message);

        } catch (JsonProcessingException e) {
            log.error("error make String from update", e.getMessage());
        }

    }
}
