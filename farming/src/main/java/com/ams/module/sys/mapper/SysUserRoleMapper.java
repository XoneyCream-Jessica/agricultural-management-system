package com.ams.module.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ams.module.sys.entity.SysRole;
import com.ams.module.sys.entity.SysUserRole;
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
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
    /*
     * @param id:用户id
     * @Description: TODO(通过用户id查询用户角色)
     * @date 2021/5/12 22:00
     * @return
     */
    List<SysRole> findRoleByUserId(String id);
}
