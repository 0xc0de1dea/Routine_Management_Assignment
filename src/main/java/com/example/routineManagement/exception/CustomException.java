package com.example.routineManagement.exception;

import com.example.routineManagement.type.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private final HttpStatus errorStatus;
    private final String errorMessage;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorStatus = errorCode.getStatus();
        this.errorMessage = errorCode.getDescription();
    }
}
