package com.shop.productservice.repository;

import com.shop.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("redisProductRepository")
public interface RedisProductRepository extends JpaRepository<Product, Long> {

}
