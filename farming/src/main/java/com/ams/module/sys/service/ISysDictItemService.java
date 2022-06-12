package com.ams.module.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ams.module.sys.entity.SysDictItem;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 字典项表 服务类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-08
 */
public interface ISysDictItemService extends IService<SysDictItem> {

    List<SysDictItem> findList(SysDictItem en);

    List<Map<String, String>> findByDictCode(String code);
}
