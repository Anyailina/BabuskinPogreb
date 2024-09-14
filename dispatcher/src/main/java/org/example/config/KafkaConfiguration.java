package org.example.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class KafkaConfiguration {
    @Bean
    public NewTopic docMessageTopic(){
        return new NewTopic("doc_message",1,(short) 1);
    }

    @Bean
    public NewTopic photoMessageTopic(){
        return new NewTopic("photo_message",1,(short) 1);
    }

    @Bean
    public NewTopic textMessageTopic(){
        return new NewTopic("text_message",1,(short) 1);
    }

    @Bean
    public NewTopic answerMessageTopic(){
        return new NewTopic("answer_message",1,(short) 1);
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new MappingJackson2MessageConverter();
    }
}
