package com.shop.productservice.dto.response;

import com.shop.productservice.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
  private Long id;
  private String receiveAddress;
  private BigDecimal amount;
  private Integer status;
  private LocalDate receiveTime;
  private List<OrderItem> orderItems;
  private BigDecimal shipFee;
}
