package com.shop.notificationservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.notificationservice.entity.Transaction;
import com.shop.notificationservice.service.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;


@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
  private final ObjectMapper objectMapper;
  private final EmailService emailService;

  @KafkaListener(topics = "payment-event", groupId = "notify payment")
  public void sendEmail(String message) throws JsonProcessingException, MessagingException, UnsupportedEncodingException {
    log.info("Receive message: " + message);
    Transaction transaction = objectMapper.readValue(message, Transaction.class);
    emailService.sendEmail(transaction);
  }
}
