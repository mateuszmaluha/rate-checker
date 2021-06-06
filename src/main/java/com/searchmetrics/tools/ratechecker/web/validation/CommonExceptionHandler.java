package com.searchmetrics.tools.ratechecker.web.validation;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DateParameterException.class)
    public final ResponseEntity<Map<String, String>> dateParameterNotValid(Exception ex) {
        return new ResponseEntity<>(Map.of("error", HttpStatus.BAD_REQUEST.getReasonPhrase(), "message", ex.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
