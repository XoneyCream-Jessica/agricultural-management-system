package com.ams.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ams.module.sys.entity.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单表 Mapper 接口
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<SysMenu> findByUserId(String id);
}
