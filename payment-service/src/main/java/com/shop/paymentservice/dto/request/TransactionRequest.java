package com.shop.paymentservice.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionRequest {

  private Long id;
  @NotNull
  private Long orderId;
  private BigDecimal amount;
  private Integer status;
  private Date receivedTime;
  private String currency;
  private String customerAccountId;
  @NotNull
  private String userId;
}
