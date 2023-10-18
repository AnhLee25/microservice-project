package com.shop.productservice.exception.handle;

import com.shop.productservice.exception.custom.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
  private final MessageSource messageSource;

  @ExceptionHandler({RuntimeException.class, NotFoundException.class})
  public ResponseEntity<ErrorResponse> handleDuplicateDataException(RuntimeException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
    String configMessage = messageSource.getMessage(e.getMessage(), null, null);
    errorResponse.setDescription(configMessage);
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }
//  TODO: 403-Forbidden
}
