package com.shop.productservice.controller;

import com.shop.productservice.dto.request.OrderRequest;
import com.shop.productservice.dto.request.OrderUpdateStatusRequest;
import com.shop.productservice.dto.response.OrderResponse;
import com.shop.productservice.entity.Order;
import com.shop.productservice.service.OrderService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  @PostMapping("/update")
  public ResponseEntity<?> updateOrder(OrderRequest request) {
    orderService.updateOrder(request);
    return ResponseEntity.ok("Update order successfully");
  }

  @PostMapping("/update-status")
  public ResponseEntity<?> updateOrderStatus(@RequestBody OrderUpdateStatusRequest request) {
    orderService.updateOrderStatus(request);
    return ResponseEntity.ok("Update order successfully");
  }

  @PostMapping("/create")
  public ResponseEntity<?> createOrder(@RequestBody @NotNull OrderRequest request) {
    orderService.createOrder(request);
    return ResponseEntity.ok("Create order successfully");
  }

  @DeleteMapping("/delete")
  public ResponseEntity<?> deleteOrder(@RequestParam Long id) {
    orderService.deleteOrder(id);
    return ResponseEntity.ok("Delete order successfully");
  }

  @GetMapping("/get")
  public ResponseEntity<OrderResponse> getOrder(@RequestParam Long orderId, Long userId) {
    return ResponseEntity.ok(orderService.getOrder(orderId, userId));
  }
}
