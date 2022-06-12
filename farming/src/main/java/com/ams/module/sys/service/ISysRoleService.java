package com.ams.module.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ams.module.sys.entity.SysRole;
import com.ams.util.Pager;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-08
 */
public interface ISysRoleService extends IService<SysRole> {

    /*
     * @param pager:分页参数
     * @param en：实体对象
     * @Description: TODO()
     * @date 2021/5/15 1:23
     * @returns com.ht.util.Pager<com.ht.module.sys.entity.SysRole>
     */
    Pager<SysRole> findPage(Pager<SysRole> pager, SysRole en);

    /**
     *
     * @param userId
     * @Description: TODO(通过用户查询角色)
     * @Return java.util.List<com.ht.module.admin.entity.SysRole>
     * @Date 2021/4/27 20:59
     */
    List<SysRole> findByUserId(String userId);


    /*
     * @param sysRole:角色对象
     * @Description: TODO(给角色设置菜单)
     * @date 2021/5/15 14:07
     * @returns void
     */
    void setMenuByRole(SysRole sysRole);
    /*
     * @param en
     * @Description: TODO(查询所有角色)
     * @date 2021/5/15 20:30
     * @returns java.util.List<com.ht.module.sys.entity.SysRole>
     */
    List<SysRole> findList(SysRole en);
}
