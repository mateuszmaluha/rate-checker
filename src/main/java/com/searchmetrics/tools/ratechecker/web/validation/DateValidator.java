package com.searchmetrics.tools.ratechecker.web.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateValidator {
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    static {
        DATE_FORMAT.setLenient(false);
    }

    public static Date parseDate(String date) {
        try {
            return DATE_FORMAT.parse(date);
        } catch (ParseException e) {
            throw new DateParameterException(String.format("Wrong format of date: %s", date));
        }
    }
}
