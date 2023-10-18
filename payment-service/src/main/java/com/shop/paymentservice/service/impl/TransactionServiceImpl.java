package com.shop.paymentservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.paymentservice.dto.request.TransactionFilterRequest;
import com.shop.paymentservice.dto.request.TransactionRequest;
import com.shop.paymentservice.dto.response.TransactionResponse;
import com.shop.paymentservice.entity.Transaction;
import com.shop.paymentservice.kafka.KafkaProducer;
import com.shop.paymentservice.repository.TransactionRepository;
import com.shop.paymentservice.service.TransactionService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository transactionRepository;
  private final ModelMapper modalMapper;
  private final KafkaProducer kafkaProducer;
  private final ObjectMapper objectMapper;

  @Value(value = "${constant.kafka.topic.payment-event}")
  private String payment_topic;

  @Override
  public Page<TransactionResponse> getTransaction(TransactionFilterRequest request) {
    Pageable pageable = PageRequest.of(request.getPageNumber() - 1, request.getPageSize());
    Page<Transaction> transactionPage =
        transactionRepository.findAll(createSearchCriteria(request), pageable);
    return transactionPage.map(
        transaction -> modalMapper.map(transaction, TransactionResponse.class));
  }

  private Specification<Transaction> createSearchCriteria(TransactionFilterRequest request) {
    return (root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      predicates.add(
          criteriaBuilder.like(
              root.get("orderId"), "%" + String.valueOf(request.getOrderId()).trim() + "%"));
      if (request.getStatus() != null) {
        Predicate statusFilter = criteriaBuilder.equal(root.get("status"), request.getStatus());
        predicates.add(statusFilter);
      }
      if (!request.getCurrency().isBlank()) {
        predicates.add(
            criteriaBuilder.like(
                root.get("currency"), "%" + String.valueOf(request.getCurrency()).trim() + "%"));
      }
      if (request.getFromReceivedDate() != null) {
        predicates.add(
            criteriaBuilder.greaterThanOrEqualTo(
                root.get("receivedDate"), request.getFromReceivedDate()));
      }
      if (request.getToReceivedDate() != null) {
        predicates.add(
            criteriaBuilder.lessThanOrEqualTo(
                root.get("receivedDate"), request.getToReceivedDate()));
      }
      if (request.getFromAmount() != null) {
        predicates.add(
            criteriaBuilder.greaterThanOrEqualTo(root.get("amount"), request.getFromAmount()));
      }
      if (request.getToAmount() != null) {
        predicates.add(
            criteriaBuilder.lessThanOrEqualTo(root.get("amount"), request.getToAmount()));
      }

      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
  }

  @Override
  public void saveTransaction(TransactionRequest request) throws JsonProcessingException {
    Transaction transaction = modalMapper.map(request, Transaction.class);
    kafkaProducer.sendMessage(payment_topic, objectMapper.writeValueAsString(transaction));
//    TODO: check số tiền thanh toán: đúng -> save + cập nhật trạng thái, sai -> response: thiếu||thừa
    transactionRepository.save(transaction);
  }
}
