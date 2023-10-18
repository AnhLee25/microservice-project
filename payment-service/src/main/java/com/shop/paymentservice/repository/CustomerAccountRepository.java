package com.shop.paymentservice.repository;

import com.shop.paymentservice.entity.CustomerAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAccountRepository extends JpaRepository<CustomerAccount, Long> {}
