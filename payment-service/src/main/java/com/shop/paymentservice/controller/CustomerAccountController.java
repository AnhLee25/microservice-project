package com.shop.paymentservice.controller;

import com.shop.paymentservice.service.CustomerAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer-account")
@RequiredArgsConstructor
public class CustomerAccountController {
    private final CustomerAccountService customerAccountService;

}
