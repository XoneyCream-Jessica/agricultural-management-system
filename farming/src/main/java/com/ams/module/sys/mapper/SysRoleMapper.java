package com.ams.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ams.module.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRole> findByUserId(String userId);
}
