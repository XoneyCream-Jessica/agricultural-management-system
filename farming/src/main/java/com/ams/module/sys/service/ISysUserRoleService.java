package com.ams.module.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ams.module.sys.entity.SysRole;
import com.ams.module.sys.entity.SysUserRole;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-08
 */
public interface ISysUserRoleService extends IService<SysUserRole> {

    /*
     * @param id:用户id
     * @Description: TODO(通过用户id查询用户角色)
     * @date 2021/5/12 22:00
     * @return
     */
    List<SysRole> findRoleByUserId(String id);
}
