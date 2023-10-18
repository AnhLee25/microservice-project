package com.shop.productservice.service;

import com.shop.productservice.dto.request.ProductRequest;
import com.shop.productservice.dto.request.ProductSearchRequest;
import com.shop.productservice.dto.response.ProductResponse;
import org.springframework.data.domain.Page;

public interface ProductService {
  void createProduct(ProductRequest request);

  void updateProduct(ProductRequest request);

  void deleteProduct(Long id);

  Page<ProductResponse> getProduct(ProductSearchRequest request);
}
