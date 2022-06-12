package com.ams.util;

import com.ams.module.bus.domain.entity.BaseEntity;

import java.util.Comparator;

public class ComparatorUtil {

    public static Comparator<BaseEntity> createIdComparator(){
       return  (o1, o2) -> (int) (o1.getId() - o2.getId());
    }
}
