package com.shop.notificationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction implements Serializable {
  private Long id;

  private Long orderId;

  private BigDecimal amount;
  private Integer status;

  private Date receivedTime;

  private String currency;
  private String customerAccountId;
  private String userId;
}
