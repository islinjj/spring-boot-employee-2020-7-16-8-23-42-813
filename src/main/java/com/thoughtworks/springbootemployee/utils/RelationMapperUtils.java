package com.thoughtworks.springbootemployee.utils;

import org.springframework.beans.BeanUtils;

/**
 * @Auther Sam Li
 * @Date 2020-08-01
 */
public class RelationMapperUtils {

    public static <T> T enAndDtoMapper(T target, Object entity) {
        BeanUtils.copyProperties(entity, target);
        return target;
    }
}
