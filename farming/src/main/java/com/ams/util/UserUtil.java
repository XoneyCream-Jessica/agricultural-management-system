package com.ams.util;


import com.ams.constant.BusConstant;
import com.ams.module.sys.entity.SysUser;
import com.ams.module.sys.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: UserUtil
 * @Description: 用户工具类
 */
@Component
public class UserUtil {
    @Autowired
    private ISysUserService iSysUserService;
    private static ISysUserService userService;
    @PostConstruct
    public void init(){
        userService=iSysUserService;
    }


    public static HttpServletRequest getRequest() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs != null ? attrs.getRequest() : null;
    }

    /*
     * @param user:用户对象
     * @Description: TODO(设置用户信息)
     * @date 2022-05-08 20:30
     * @return
     */
    public static void setUser(SysUser user) {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
        }
        session.setAttribute(BusConstant.SESSION_USER, user);
    }

    /*
     * @Description: TODO(获取用户信息)
     * @date 2022-05-08 20:30
     * @return
     */
    public static SysUser getUser() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
        }
        SysUser user = ((SysUser) session.getAttribute(BusConstant.SESSION_USER));
        if (user == null) {
           //去查询
            String username= SecurityContextHolder.getContext().getAuthentication().getName();
            user=userService.getUserInfoByUsername(username);
            user=userService.getUserByid(user.getId());
            setUser(user);
        }

        if (user == null) {
            return null;
        }
        return user;
    }


    /*
     * @Description: TODO(获取用户id)
     * @date 2022-05-08 20:30
     * @return
     */
    public static String getUserId() {
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
        }

        SysUser user =getUser();
        if (user == null) {
            return null;
        }
        return user.getId();
    }

    /*
     * @Description: TODO(清除用户信息)
     * @date 2022-05-08 20:29
     * @return
     */
    public static void clear(){
        HttpSession session = null;
        try {
            session = getRequest().getSession();
        } catch (Exception e) {
        }
       session.setAttribute(BusConstant.SESSION_USER,null);
    }

}