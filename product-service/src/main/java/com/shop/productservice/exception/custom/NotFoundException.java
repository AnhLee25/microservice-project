package com.shop.productservice.exception.custom;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotFoundException extends RuntimeException {
  String message;

  public NotFoundException(String message) {
    super(message);
    this.message = message;
  }
}
