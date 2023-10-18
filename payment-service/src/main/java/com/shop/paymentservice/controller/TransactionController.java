package com.shop.paymentservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.shop.paymentservice.dto.request.TransactionFilterRequest;
import com.shop.paymentservice.dto.request.TransactionRequest;
import com.shop.paymentservice.dto.response.TransactionResponse;
import com.shop.paymentservice.entity.Transaction;
import com.shop.paymentservice.service.TransactionService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
public class TransactionController {
  private final TransactionService transactionService;

  @PostMapping("/save")
  public ResponseEntity<?> saveTransaction(@NotNull @RequestBody TransactionRequest request) throws JsonProcessingException {
    transactionService.saveTransaction(request);
    return ResponseEntity.ok("Order has been paid successfully");
  }

  @GetMapping("/get-transaction")
  public ResponseEntity<Page<TransactionResponse>> getTransaction(
      @RequestBody TransactionFilterRequest request) {
    return ResponseEntity.ok(transactionService.getTransaction(request));
  }
}
