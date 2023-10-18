package com.shop.productservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRedis {
  private Long id;
  private String name;
  private String color;
  private String size;
  private BigDecimal originPrice;
  private BigDecimal price;
  private Long soldQuantity;
  private Long remainQuantity;
}
