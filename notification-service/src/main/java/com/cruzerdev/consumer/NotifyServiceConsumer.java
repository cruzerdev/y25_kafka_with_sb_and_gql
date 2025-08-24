package com.cruzerdev.consumer;


import com.cruzerdev.dto.TransactionEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NotifyServiceConsumer {

    @KafkaListener(
            topics = "tx-event-v2", groupId = "trans-event-group"
    )
    public void onMessage(ConsumerRecord<String, TransactionEvent> consumerRecord) throws JsonProcessingException {

        log.info("ConsumerRecord : {} ", consumerRecord);
    }
}
