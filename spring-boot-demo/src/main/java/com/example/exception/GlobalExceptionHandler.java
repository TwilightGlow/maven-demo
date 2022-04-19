package com.example.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Javen
 * @date 2022/4/13
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(Exception e) {
        log.error("This is error: {}, Exception type: {}", e.getMessage(), e.getClass());
        return "Error!";
    }

    @ExceptionHandler(value = Throwable.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleThrowable(Throwable throwable) {
        log.error("This is error", throwable);
        return "Error!";
    }
}
