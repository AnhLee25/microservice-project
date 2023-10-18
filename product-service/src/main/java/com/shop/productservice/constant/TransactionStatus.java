package com.shop.productservice.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionStatus {
  FAIL(0),
  SUCCESS(1);
  private final Integer value;
}
