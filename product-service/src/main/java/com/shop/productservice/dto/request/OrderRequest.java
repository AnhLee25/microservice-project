package com.shop.productservice.dto.request;

import com.shop.productservice.constant.OrderStatus;
import com.shop.productservice.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequest {
  private Long id;
  private String receiveAddress;
  private Integer status = OrderStatus.APPROVE_WAITING.getValue();
  private LocalDate receiveTime;
  private List<OrderItem> orderItems;
  private BigDecimal shipFee;
}
