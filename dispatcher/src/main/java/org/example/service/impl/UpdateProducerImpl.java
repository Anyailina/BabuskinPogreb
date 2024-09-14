package org.example.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.service.UpdateProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;


@Service
@Slf4j
public class UpdateProducerImpl implements UpdateProducer {
    private final KafkaTemplate<String,String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public UpdateProducerImpl(KafkaTemplate<String,String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public void produce(String kafkaTopic, Update update) {
        kafkaTemplate.send("text_message", "kls;");
//        try {
//            String message = objectMapper.writeValueAsString(update);
//
//
//        } catch (JsonProcessingException e) {
//            log.error("error make String from update", e.getMessage());
//        }
    }
}
