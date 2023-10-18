package com.shop.paymentservice.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
  private final KafkaTemplate kafkaTemplate;

  public void sendMessage(String topicName, String message) {
    CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topicName, message);
    future.whenComplete((result, ex) -> {
      if (ex == null) {
        System.out.println("Sent message=[" + message +
                "] with offset=[" + result.getRecordMetadata().offset() + "]");
      } else {
        System.out.println("Unable to send message=[" +
                message + "] due to : " + ex.getMessage());
      }
    });
  }
}
