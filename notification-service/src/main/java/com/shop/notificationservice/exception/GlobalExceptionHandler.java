package com.shop.notificationservice.exception;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalExceptionHandler {
  private final MessageSource messageSource;

  @ExceptionHandler({ConnectException.class})
  public ResponseEntity<ErrorResponse> handleConnectException(RuntimeException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
    return getErrorResponse(e, errorResponse);
  }

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setCode(HttpStatus.BAD_REQUEST.value());
    return getErrorResponse(e, errorResponse);
  }

  private ResponseEntity<ErrorResponse> getErrorResponse(
      RuntimeException e, ErrorResponse errorResponse) {
    try {
      String configMessage = messageSource.getMessage(e.getMessage(), null, null);
      errorResponse.setDescription(configMessage);
    } catch (Exception exception) {
      log.error(exception.getMessage());
      errorResponse.setDescription(e.getMessage());
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
  }
}
