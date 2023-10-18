package com.shop.productservice.service.impl;

import com.shop.productservice.dto.request.OrderRequest;
import com.shop.productservice.dto.request.OrderUpdateStatusRequest;
import com.shop.productservice.dto.request.ProductRequest;
import com.shop.productservice.dto.response.OrderResponse;
import com.shop.productservice.entity.Order;
import com.shop.productservice.entity.OrderItem;
import com.shop.productservice.entity.Product;
import com.shop.productservice.exception.custom.NotFoundException;
import com.shop.productservice.repository.OrderRepository;
import com.shop.productservice.service.OrderService;
import com.shop.productservice.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {
  private final ProductService productService;
  private final OrderRepository orderRepository;
  private final ModelMapper modelMapper;

  @Override
  public void updateOrderStatus(OrderUpdateStatusRequest request) {
    Order order =
        orderRepository
            .findById(request.getId())
            .orElseThrow(
                () -> {
                  throw new RuntimeException("order.not-found");
                });
    order.setStatus(request.getStatus());
    orderRepository.save(order);
    log.info("Update order {} successfully", order.getId());
  }

  @Override
  public void updateOrder(OrderRequest request) {
    Order order =
        orderRepository
            .findById(request.getId())
            .orElseThrow(
                () -> {
                  throw new RuntimeException("order.not-found");
                });

    order.setStatus(request.getStatus());
    order.setOrderItems(request.getOrderItems());
    order.setStatus(request.getStatus());
    order.setReceiveAddress(request.getReceiveAddress());
    order.setShipFee(request.getShipFee());
    order.setReceiveTime(request.getReceiveTime());
    orderRepository.save(order);
    log.info("Update order {} successfully", order.getId());
  }

  @Override
  public void createOrder(OrderRequest request) {
    if (request.getOrderItems().isEmpty()) {
      throw new RuntimeException("order-item.null");
    }
    Order order = modelMapper.map(request, Order.class);
    BigDecimal amount = BigDecimal.valueOf(0);
    order.setAmount(calculateOrderAmount(order.getOrderItems()));
    orderRepository.save(order);
  }

  private BigDecimal calculateOrderAmount(List<OrderItem> orderItemList) {
    BigDecimal amount = BigDecimal.valueOf(0);
    for (OrderItem orderItem : orderItemList) {
      amount =
          amount.add(
              orderItem
                  .getProduct()
                  .getOriginPrice()
                  .multiply(BigDecimal.valueOf(orderItem.getQuantity())));
      updateProductQuantity(orderItem);
    }
    return amount;
  }

  private void updateProductQuantity(OrderItem orderItem) {
    Product product = orderItem.getProduct();
    long remainQuantity = product.getRemainQuantity() - orderItem.getQuantity();
    if (remainQuantity < 0) {
      throw new RuntimeException("product.out-stock");
    }
    product.setRemainQuantity(remainQuantity);
    product.setSoldQuantity(product.getSoldQuantity() + orderItem.getQuantity());
    ProductRequest productRequest = modelMapper.map(product, ProductRequest.class);
    productService.updateProduct(productRequest);
    //    COMMENT: save từng product -> hiệu suất thấp
    //    TODO: Tổng hợp các product cần cập nhật -> List -> saveAll
  }

  @Override
  public OrderResponse getOrder(Long orderId, Long userId) {
    Order order =
        orderRepository
            .findById(orderId)
            .orElseThrow(() -> new NotFoundException("order.not-found"));
    order.setOrderItems(order.getOrderItems());
    return modelMapper.map(order, OrderResponse.class);
  }

  @Override
  public void deleteOrder(Long id) {
    orderRepository
        .findById(id)
        .ifPresentOrElse(
            orderRepository::delete,
            () -> {
              throw new NotFoundException("order.not-found");
            });
  }
}
