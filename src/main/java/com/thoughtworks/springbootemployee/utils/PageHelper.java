package com.thoughtworks.springbootemployee.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther Sam Li
 * @Date 2020-07-28
 */
public class PageHelper {
    public static <T> List<T> findByPage(int page, int pageSize, List<T> list) {
        List<T> result = new ArrayList<>();
        if (list.size() < (page - 1) * pageSize) {
            return result;
        }
        int startIndex = (page - 1) * pageSize;
        int endIndex = page * pageSize - 1;
        result = list.stream().skip(startIndex).limit(endIndex - startIndex + 1).collect(Collectors.toList());
        return result;
    }
}
