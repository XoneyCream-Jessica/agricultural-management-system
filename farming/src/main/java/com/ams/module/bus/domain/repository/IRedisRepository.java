package com.ams.module.bus.domain.repository;

import com.ams.module.bus.domain.entity.BaseEntity;
import com.ams.util.RedisPager;

import java.util.List;

/**
 * redis分页查询
 */
public interface IRedisRepository<T extends BaseEntity> {

    default RedisPager<T> findPager(RedisPager<T> pager, T param) {
        List<T> data = findList(param);
        pager.setRecords(data);
        return pager;
    }

    List<T> findList(T param);

}
