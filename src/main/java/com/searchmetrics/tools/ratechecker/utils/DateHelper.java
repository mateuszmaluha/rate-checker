package com.searchmetrics.tools.ratechecker.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateHelper {
    public static Date getDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    public static Date getCurrentTime() {
        return getDate(LocalDateTime.now());
    }
}
