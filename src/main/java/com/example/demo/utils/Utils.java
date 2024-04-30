package com.example.demo.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.time.LocalDate;
import java.util.*;

public abstract class Utils {
    public static boolean isBeforeDate(LocalDate a, LocalDate b) {
        return !a.isAfter(b);
    }

    public static void copyNonNullProperties(Object source, Object destination, String ...ignore){
        String[] nullPropertyNames = getNullPropertyNames(source);
        List<String> toIgnore = new ArrayList<>();
        Collections.addAll(toIgnore, ignore);
        Collections.addAll(toIgnore, nullPropertyNames);

        BeanUtils.copyProperties(source, destination, toIgnore.toArray(String[]::new));
    }

    private static String[] getNullPropertyNames (Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for(java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
