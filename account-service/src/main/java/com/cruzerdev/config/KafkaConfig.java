package com.cruzerdev.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;


@Configuration
@Profile("local")
public class KafkaConfig {
    @Value("${spring.kafka.topic}")
    public String topic;

    @Bean
    public NewTopic transactionsEvents(){
        return TopicBuilder.name(topic)
                .partitions(2)
                .replicas(2)
                .build();
    }


}
