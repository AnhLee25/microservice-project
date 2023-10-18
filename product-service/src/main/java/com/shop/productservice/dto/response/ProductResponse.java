package com.shop.productservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
  private Long id;
  private String name;
  private String color;
  private String size;
  private BigDecimal originPrice;
  private BigDecimal price;
  private Long soldQuantity;
  private Long remainQuantity;
}
