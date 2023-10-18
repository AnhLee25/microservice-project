package com.shop.paymentservice.dto.request;

import jakarta.validation.constraints.Min;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagingBaseRequest {
  @Min(value = 1)
  private int pageNumber;

  @Min(value = 1)
  private int pageSize;
}
