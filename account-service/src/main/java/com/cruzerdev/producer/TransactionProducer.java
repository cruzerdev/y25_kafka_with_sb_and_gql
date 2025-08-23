package com.cruzerdev.producer;

import com.cruzerdev.config.KafkaConfig;
import com.cruzerdev.dto.TransactionEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class TransactionProducer {

    KafkaTemplate<String, TransactionEvent> kafkaTemplate;
    KafkaTemplate<String, String> kafkaTemplate1;
    ObjectMapper objectMapper;

    @Autowired
    KafkaConfig kafkaConfig;



    @Value("${spring.kafka.topic}")
    public String topic;

    public TransactionProducer(KafkaTemplate<String, TransactionEvent> kafkaTemplate, KafkaTemplate<String, String> kafkaTemplate1, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTemplate1 = kafkaTemplate1;
        this.objectMapper = objectMapper;
    }

    public void sendNotificationOnInsufficientBalance(String user, double amount){

        String value = "User: "+user+" has Insufficient balance."+amount
                +" can not be withdraw.";
        kafkaTemplate1.sendDefault(user, value);
    }

    public CompletableFuture<SendResult<String, TransactionEvent>> sendTransactionsEvent(TransactionEvent transactionEvent) throws JsonProcessingException {

        String key = transactionEvent.getAccountId();
        String value = objectMapper.writeValueAsString(transactionEvent);

        var completableFuture = kafkaTemplate.send(topic,key, transactionEvent);
        return completableFuture
                .whenComplete((sendResult, throwable) -> {
                    if (throwable != null) {
                        handleFailure(key, value, throwable);
                    } else {
                        handleSuccess(key, value, sendResult);

                    }
                });
    }

    private void handleFailure(String key, String value, Throwable ex) {
        log.error("Error Sending the Message and the exception is {}", ex.getMessage());
//        try {
//            throw ex;
//        } catch (Throwable throwable) {
//            log.error("Error in OnFailure: {}", throwable.getMessage());
//        }


    }

    private void handleSuccess(String key, String value, SendResult<String, TransactionEvent> result) {
        log.info("Message Sent SuccessFully for the key : {} and the value is {} , partition is {}", key, value, result.getRecordMetadata().partition());
    }
}
