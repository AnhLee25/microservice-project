package com.shop.notificationservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse implements Serializable {
  private Long id;

  private String email;

  private boolean enabled;

  private String firstName;

  private String lastName;

  private String password;

  private String photos;
}
