package com.ams.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ams.module.sys.entity.SysDictItem;

import java.util.List;

/**
 * <p>
 * 字典项表 Mapper 接口
 * </p>
 *
 * @author Auto-generator
 * @since  2022-05-07
 */
public interface SysDictItemMapper extends BaseMapper<SysDictItem> {

    List<SysDictItem> findByDictCode(String code);
}
