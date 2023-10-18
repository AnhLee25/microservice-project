package com.shop.productservice.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {
  PAYMENT_WAITING(0),
  PAID(1),
  APPROVE_WAITING(2),
  PREPARING(3),
  SHIPPING(4),
  DONE(5),
  RETURN(6);
  private final Integer value;
}
