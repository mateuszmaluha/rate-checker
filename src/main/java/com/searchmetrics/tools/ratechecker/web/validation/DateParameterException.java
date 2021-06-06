package com.searchmetrics.tools.ratechecker.web.validation;

public class DateParameterException extends RuntimeException {
    public DateParameterException(String format) {
        super(format);
    }
}
