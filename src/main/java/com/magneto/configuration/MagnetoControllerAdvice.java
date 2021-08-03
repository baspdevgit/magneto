package com.magneto.configuration;

import com.magneto.crosscutting.constants.Constants;
import com.magneto.crosscutting.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MagnetoControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ArithmeticException.class})
    protected ResponseEntity<Object> handleRatioOnZero(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, Constants.RATIO_ERROR_MESSAGE, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<Object> handleBusiness(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
