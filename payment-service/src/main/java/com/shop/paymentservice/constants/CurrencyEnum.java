package com.shop.paymentservice.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CurrencyEnum {
  VND("VND", "Vietnamese Dong"),
  USD("USD", "US Dollar"),
  EUR("EUR", "Euro"),
  CNY("CNY", "Chinese Yuan"),
  JPY("JPY", "Japanes Yen");
  private final String code;
  private final String description;
}
