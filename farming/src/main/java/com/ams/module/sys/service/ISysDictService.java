package com.ams.module.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ams.module.sys.entity.SysDict;
import com.ams.util.Pager;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-08
 */
public interface ISysDictService extends IService<SysDict> {

    Pager<SysDict> findPage(Pager<SysDict> pager, SysDict sysDict);
}
