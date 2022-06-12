package com.ams.module.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ams.module.sys.entity.SysMenu;
import com.ams.module.sys.entity.SysMenuRole;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author Auto-generator
 * @since 2022-05-08
 */
public interface ISysMenuService extends IService<SysMenu> {

    /*
     * @param uid:用户id
     * @Description: TODO(获取用户菜单树)
     * @date 2021/5/14 20:43
     * @returns com.ht.module.sys.entity.SysMenu
     */
    List<SysMenu> findTreeByUid(String uid);

    /*
     * @param
     * @Description: TODO(查询出菜单树)
     * @date 2021/5/15 13:46
     * @returns com.ht.util.JsonResult
     */
    List<SysMenu> findMenuTree();

    /*
     * @param rid:角色id
     * @Description: TODO(按角色查询出菜单)
     * @date 2021/5/15 13:49
     * @returns java.util.List<com.ht.module.sys.entity.SysMenu>
     */
    List<SysMenuRole> findMenuRole(String rid);

    void delById(String id);
}
