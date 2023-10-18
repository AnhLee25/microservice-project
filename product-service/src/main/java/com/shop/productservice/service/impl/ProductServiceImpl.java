package com.shop.productservice.service.impl;

import com.shop.productservice.dto.request.ProductRequest;
import com.shop.productservice.dto.request.ProductSearchRequest;
import com.shop.productservice.dto.response.ProductResponse;
import com.shop.productservice.entity.Product;
import com.shop.productservice.repository.ProductRepository;
import com.shop.productservice.service.ProductService;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
  private final ProductRepository productRepository;
  private final ModelMapper modelMapper;

  @Override
  public void createProduct(ProductRequest request) {
    Product product = modelMapper.map(request, Product.class);
    productRepository.save(product);
  }

  @Override
  public void updateProduct(ProductRequest request) {
    if (request.getId() == null) {
      throw new RuntimeException("id.null");
    }
    Product product =
        productRepository
            .findById(request.getId())
            .orElseThrow(() -> new RuntimeException("product.not-found"));
    product.setName(request.getName());
    product.setColor(request.getColor());
    product.setSize(request.getSize());
    product.setPrice(request.getPrice());
    product.setOriginPrice(request.getOriginPrice());
    product.setDescription(request.getDescription());
    product.setSoldQuantity(request.getSoldQuantity());
    product.setRemainQuantity(request.getRemainQuantity());
    productRepository.save(product);
  }

  @Override
  public void deleteProduct(Long id) {
    Product product =
        productRepository.findById(id).orElseThrow(() -> new RuntimeException("product.not-found"));
    productRepository.delete(product);
  }

  @Override
  public Page<ProductResponse> getProduct(ProductSearchRequest request) {
    Page<Product> products =
        productRepository.findAll(
            createCriteriaSearch(request),
            PageRequest.of(request.getPageNumber() - 1, request.getPageSize()));
    return products.map(product -> modelMapper.map(product, ProductResponse.class));
  }

  private Specification<Product> createCriteriaSearch(ProductSearchRequest request) {
    return ((root, query, criteriaBuilder) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (request.getId() != null) {
        predicates.add(criteriaBuilder.equal(root.get("id"), request.getId()));
      }
      if (!request.getName().isBlank()) {
        predicates.add(criteriaBuilder.like(root.get("name"), request.getName().trim()));
      }
      if (request.getColor() != null) {
        predicates.add(criteriaBuilder.like(root.get("color"), request.getColor()));
      }
      if (request.getBelowPrice() != null) {
        predicates.add(
            criteriaBuilder.greaterThanOrEqualTo(root.get("price"), request.getBelowPrice()));
      }
      if (request.getAbovePrice() != null) {
        predicates.add(
            criteriaBuilder.lessThanOrEqualTo(root.get("price"), request.getAbovePrice()));
      }
      return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    });
  }
}
