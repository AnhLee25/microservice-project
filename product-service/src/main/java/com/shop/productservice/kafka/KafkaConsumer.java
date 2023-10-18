package com.shop.productservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.productservice.constant.OrderStatus;
import com.shop.productservice.constant.TransactionStatus;
import com.shop.productservice.dto.request.OrderUpdateStatusRequest;
import com.shop.productservice.dto.request.Transaction;
import com.shop.productservice.entity.Order;
import com.shop.productservice.repository.OrderRepository;
import com.shop.productservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumer {
  private final ObjectMapper objectMapper;
  private final OrderService orderService;
  private final OrderRepository orderRepository;
  //    @Value(value = "${constant.kafka.group-id}")
  //    private String kafkaGroupId;
  //    @Value(value = "${constant.kafka.topic.payment-event}")
  //    private String paymentTopic;
  @KafkaListener(topics = "payment-event", groupId = "payment")
  public void getTransaction(String message) throws JsonProcessingException {
    Transaction transaction = objectMapper.readValue(message, Transaction.class);
    Long orderId = transaction.getOrderId();
    BigDecimal amount = transaction.getAmount();
    Integer status = transaction.getStatus();
    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(() -> new RuntimeException("order.not-found"));
    if(!amount.equals(order.getAmount())){
      throw new RuntimeException("paycheck.fail");
    }
    if (TransactionStatus.SUCCESS.getValue().equals(status)) {
      orderService.updateOrderStatus(
          new OrderUpdateStatusRequest(orderId, OrderStatus.PAID.getValue()));

      log.info("Order {} has been paid successfully", orderId);
    }
    System.out.println("Received message: " + message);
  }
}
