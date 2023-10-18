package com.shop.productservice;

import com.shop.productservice.entity.OrderItem;
import com.shop.productservice.entity.Product;
import com.shop.productservice.repository.OrderItemRepository;
import com.shop.productservice.repository.OrderRepository;
import com.shop.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RepoRest {
  @Autowired private ProductRepository productRepository;
  @Autowired private OrderRepository orderRepository;
  @Autowired private OrderItemRepository orderItemRepository;

  @Test
  void testRepo() {
    Product product1 =
        Product.builder()
            .name("Computer")
            .color("Black")
            .description("Latest version")
            .originPrice(BigDecimal.valueOf(132.56))
            .price(BigDecimal.valueOf(99.99))
            .size("15.6")
            .soldQuantity(1L)
            .remainQuantity(20L)
            .build();
    Product product2 =
        Product.builder()
            .name("Computer")
            .color("Blue")
            .description("Latest version")
            .originPrice(BigDecimal.valueOf(120.0))
            .price(BigDecimal.valueOf(99.99))
            .size("15.6")
            .soldQuantity(10L)
            .remainQuantity(20L)
            .build();
    Product product3 =
        Product.builder()
            .name("Computer")
            .color("Silver")
            .description("Latest version")
            .originPrice(BigDecimal.valueOf(150.0))
            .price(BigDecimal.valueOf(99.99))
            .size("15.6")
            .soldQuantity(10L)
            .remainQuantity(20L)
            .build();
    List<Product> products = List.of(product1, product2, product3);
//    OrderItem orderItem = OrderItem.
    productRepository.saveAll(products);
  }
}
