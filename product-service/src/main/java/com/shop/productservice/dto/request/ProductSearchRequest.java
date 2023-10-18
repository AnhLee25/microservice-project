package com.shop.productservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchRequest extends PagingBaseRequest {
  private Long id;
  private String name;
  private String color;
  private String size;
  private BigDecimal belowPrice;
  private BigDecimal abovePrice;
}
