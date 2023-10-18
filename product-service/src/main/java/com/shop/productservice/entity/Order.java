package com.shop.productservice.entity;

import com.shop.productservice.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {
  @Column(name = "receive_address")
  private String receiveAddress;

  private BigDecimal amount;

  private Integer status = OrderStatus.APPROVE_WAITING.getValue();

  @Column(name = "receive_time")
  private LocalDate receiveTime;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderItem> orderItems;

  @Column(name = "ship_fee")
  private BigDecimal shipFee;
  @Column(name = "user_Id")
  private Long userId;
}
