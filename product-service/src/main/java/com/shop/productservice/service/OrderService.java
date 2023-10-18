package com.shop.productservice.service;

import com.shop.productservice.dto.request.OrderRequest;
import com.shop.productservice.dto.request.OrderUpdateStatusRequest;
import com.shop.productservice.dto.response.OrderResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
  void updateOrderStatus(OrderUpdateStatusRequest request);

  void updateOrder(OrderRequest request);
  void createOrder(OrderRequest request);
  OrderResponse getOrder(Long orderId, Long userId);
  void deleteOrder(Long id);
}
