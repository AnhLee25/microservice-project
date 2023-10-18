package com.shop.productservice.controller;

import com.shop.productservice.dto.request.ProductRequest;
import com.shop.productservice.dto.request.ProductSearchRequest;
import com.shop.productservice.dto.response.ProductResponse;
import com.shop.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService productService;

  @PostMapping("/create")
  public ResponseEntity<?> createProduct(@RequestBody ProductRequest request) {
    productService.createProduct(request);
    return ResponseEntity.ok("Create new product successfully");
  }

  @PostMapping("/update")
  public ResponseEntity<?> updateProduct(@RequestBody ProductRequest request) {
    productService.updateProduct(request);
    return ResponseEntity.ok("Update product successfully");
  }

  @PostMapping("/delete")
  public ResponseEntity<?> deleteProduct(@RequestParam Long id) {
    productService.deleteProduct(id);
    return ResponseEntity.ok("Delete product successfully");
  }

  @PostMapping("/get")
  public ResponseEntity<Page<ProductResponse>> getProduct(
      @RequestBody ProductSearchRequest request) {
    return ResponseEntity.ok(productService.getProduct(request));
  }
}
