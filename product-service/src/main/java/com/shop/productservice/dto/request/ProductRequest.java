package com.shop.productservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {
  private Long id;

  @NotBlank(message = "Product's name can not be blank")
  private String name;

  private String color;
  private String size;
  private BigDecimal originPrice;
  private BigDecimal price;
  private Long soldQuantity;
  private Long remainQuantity;
  private String description;
}
