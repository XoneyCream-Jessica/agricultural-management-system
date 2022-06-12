package com.ams.module.sys.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ams.module.sys.entity.SysUser;
import com.ams.util.Pager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-07
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    Pager<SysUser> findPage(@Param("pager") Pager<SysUser> pager,@Param(Constants.WRAPPER) QueryWrapper<SysUser> qw);
}
