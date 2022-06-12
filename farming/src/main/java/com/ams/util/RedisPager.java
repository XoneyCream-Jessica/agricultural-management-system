package com.ams.util;

import com.ams.module.bus.domain.entity.BaseEntity;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Comparator;
import java.util.List;

/**
 * redis分页器
 */
public class RedisPager<T extends BaseEntity> extends Pager<T> {

    private static final Comparator<BaseEntity> IdComparator = ComparatorUtil.createIdComparator();

    @Override
    public Page<T> setRecords(List<T> records) {
        records.sort(IdComparator);
        setTotal(records.size());
        long currentPage = getCurrent();
        long size = getSize();
        long total = getTotal();
        int startIndex = (int) (size * (currentPage - 1));
        int endIndex = (int) (size * (currentPage));
        endIndex = endIndex > total ? (int) total : endIndex;
        return super.setRecords(records.subList(startIndex, endIndex));
    }
}
