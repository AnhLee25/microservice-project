package com.shop.notificationservice.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ConnectException extends RuntimeException {
    private String message;
    public ConnectException(String message){
        super(message);
        this.message = message;
    }
}
