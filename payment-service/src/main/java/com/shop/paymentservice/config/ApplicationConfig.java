package com.shop.paymentservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
  @Bean
  ModelMapper modelMapper() {
    return new ModelMapper();
  }

  @Bean
  ObjectMapper objectMapper() {
    return new ObjectMapper();
  }
}
