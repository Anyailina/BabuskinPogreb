package org.example.node.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;

@Configuration
public class KafkaConfiguration {
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new MappingJackson2MessageConverter();
    }

}
