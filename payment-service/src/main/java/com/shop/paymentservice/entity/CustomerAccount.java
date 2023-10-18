package com.shop.paymentservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_account")
public class CustomerAccount {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String accountHolder;
  private String accountNumber;
  private String cardNumber;

  @Column(name = "user_id")
  private String userId;

  private String validFrom;
}
