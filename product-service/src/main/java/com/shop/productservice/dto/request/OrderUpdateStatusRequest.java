package com.shop.productservice.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderUpdateStatusRequest {
  private Long id;
  private Integer status;
}
