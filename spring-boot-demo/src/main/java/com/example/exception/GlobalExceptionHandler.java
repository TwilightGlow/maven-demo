package com.example.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.List;

/**
 * @author Javen
 * @date 2022/4/13
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 用于控制层的实体类参数
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String bindException(BindException ex) {
        log.error("BindException:", ex);
        StringBuilder msg = new StringBuilder();
        List<FieldError> fieldErrors = ex.getFieldErrors();
        fieldErrors.forEach((oe) ->
                msg.append("参数:[").append(oe.getObjectName())
                        .append(".").append(oe.getField())
                        .append("]的传入值:[").append(oe.getRejectedValue()).append("]与预期的字段类型不匹配")
                        .append("\n")
        );
        msg.deleteCharAt(msg.length() - 1);
        return msg.toString();
    }

    // 用于控制层非实体类参数，类上加了@Validated注解，Hibernate Validator校验异常
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("ConstraintViolationException:", ex);
        StringBuilder msg = new StringBuilder();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            msg.append(violation.getPropertyPath()).append(": ").append(violation.getMessage()).append("\n");
        }
        msg.deleteCharAt(msg.length() - 1);
        return msg.toString();
    }

    // 用于控制层非实体类参数，类上没有加@Validated注解，Spring MVC校验异常
    @ExceptionHandler(HandlerMethodValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleHandlerMethodValidationException(HandlerMethodValidationException ex) {
        log.error("HandlerMethodValidationException:", ex);
        StringBuilder msg = new StringBuilder();
        for (MessageSourceResolvable error : ex.getAllErrors()) {
            msg.append(error.getDefaultMessage()).append("\n");
        }
        msg.deleteCharAt(msg.length() - 1);
        return msg.toString();
    }

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
