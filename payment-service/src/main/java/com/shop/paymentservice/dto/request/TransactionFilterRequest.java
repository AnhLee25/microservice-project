package com.shop.paymentservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionFilterRequest extends PagingBaseRequest {
  private Long orderId;
  private BigDecimal fromAmount;
  private BigDecimal toAmount;
  private Integer status;
  private String currency;
  private String customerAccountId;
  private Date fromReceivedDate;
  private Date toReceivedDate;
}
