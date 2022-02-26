package com.ryzhov.daniil.interfaces;

import com.ryzhov.daniil.exceptions.DatesToCronConvertException;

import java.util.List;

public interface DatesToCronConverter {
    String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    String convert(List<String> datesList) throws DatesToCronConvertException;

    String getImplementationInfo();
}
