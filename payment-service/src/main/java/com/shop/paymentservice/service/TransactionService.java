package com.shop.paymentservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shop.paymentservice.dto.request.TransactionFilterRequest;
import com.shop.paymentservice.dto.request.TransactionRequest;
import com.shop.paymentservice.dto.response.TransactionResponse;
import org.springframework.data.domain.Page;

public interface TransactionService {
  Page<TransactionResponse> getTransaction(TransactionFilterRequest request);

  void saveTransaction(TransactionRequest transaction) throws JsonProcessingException;
}
