package com.mk.example.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public final class DateConverter {

    private DateConverter() {}

    public static Date toDate(LocalDateTime dateTime) {
        return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate toLocalDate(Date date) {
        return ZonedDateTime
            .ofInstant(date.toInstant(), ZoneId.systemDefault())
            .toLocalDate();
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        return ZonedDateTime
            .ofInstant(date.toInstant(), ZoneId.systemDefault())
            .toLocalDateTime();
    }
}
