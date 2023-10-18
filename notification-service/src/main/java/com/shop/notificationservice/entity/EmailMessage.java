package com.shop.notificationservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "email_message")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailMessage {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private String id;

  private String senderEmail;
  private String receiverEmail;
  private String message;
  private String fileUrl;
}
