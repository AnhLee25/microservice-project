package com.shop.paymentservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
  private Long id;
  private Long orderId;
  private BigDecimal amount;
  private Integer status;
  private Date receivedTime;
  private String currency;
  private String customerAccountId;
}
