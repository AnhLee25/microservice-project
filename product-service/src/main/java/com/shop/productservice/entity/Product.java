package com.shop.productservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.math.BigDecimal;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseEntity {
  @NotNull(message = "Product's name can not be null")
  private String name;

  private String color;

  private String size;

  @Column(name = "origin_price", precision = 8, scale = 2)
  private BigDecimal originPrice;

  @Column(name = "price", precision = 8, scale = 2)
  private BigDecimal price;

  @Column(name = "sold_quantity")
  private Long soldQuantity;

  @Column(name = "remain_quantity")
  private Long remainQuantity;

  private String description;
}
