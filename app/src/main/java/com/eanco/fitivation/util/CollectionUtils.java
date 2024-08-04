package com.eanco.fitivation.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CollectionUtils {
    public static List<Integer> stringToIntegerList(String values) {
        List<String> strList = Arrays.asList(StringUtils.split(values, ","));
        return strList.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Integer> setToIntegerList(Set<? extends Object> values) {
        return org.apache.commons.collections4.CollectionUtils.emptyIfNull(values).stream()
                .map(Object::toString)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static Set<String> setToStringSet(Set<? extends Object> values) {
        return org.apache.commons.collections4.CollectionUtils.emptyIfNull(values).stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
    }
}
