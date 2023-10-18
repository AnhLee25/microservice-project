package com.shop.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@Table(name = "transaction")
public class Transaction implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "order_id")
  private Long orderId;

  private BigDecimal amount;
  private Integer status;

  @Column(name = "received_time")
  private Date receivedTime;

  private String currency;
  private String customerAccountId;
  private String userId;
}
