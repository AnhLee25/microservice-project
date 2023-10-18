package com.shop.productservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PagingBaseRequest {
  @Min(value = 1, message = "page-number.valid")
  @NotNull(message = "page-number.valid")
  private int pageNumber;

  @Min(value = 1, message = "page-size.valid")
  @NotNull(message = "page-size.valid")
  private int pageSize;
}
