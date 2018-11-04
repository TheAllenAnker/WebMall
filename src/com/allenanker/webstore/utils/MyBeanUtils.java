package com.allenanker.webstore.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

import java.util.Map;

public class MyBeanUtils {
    public static void populate(Object obj, Map<String, String[]> map) {
        try {
            // Set DateConverter for class Date
            DateConverter dt = new DateConverter();
            dt.setPattern("yyyy-MM-dd");
            ConvertUtils.register(dt, java.util.Date.class);
            BeanUtils.populate(obj, map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
