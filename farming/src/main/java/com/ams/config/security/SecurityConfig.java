package com.ams.config.security;

import com.ams.config.security.handler.LocalAccessDeniedHandler;
import com.ams.config.security.handler.LocalAuthenticationFailureHandler;
import com.ams.config.security.handler.LocalAuthenticationSuccessHandler;
import com.ams.config.security.handler.LocalLogoutSuccessHandler;
import com.ams.filter.VerifyCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @ProjectName: AMS
 * @ClassName: SecurityConfig
 * @Author: chenxi
 * @Description: security配置类
 * @Date: 2022/5/5 11:56
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private LocalAuthenticationSuccessHandler localAuthenticationSuccessHandler;    //登录成功处理
    @Autowired
    private LocalAuthenticationFailureHandler localAuthenticationFailureHandler;    //登录失败处理
    @Autowired
    private LocalAccessDeniedHandler localAccessDeniedHandler;    //无权访问处理
    @Autowired
    private LocalLogoutSuccessHandler localLogoutSuccessHandler;    //退出登录
    @Autowired
    private VerifyCodeFilter verifyCodeFilter;                  //验证码拦截器

    @Autowired
    private DataSource dataSource;      //数据源

    /*配置对象-实现记住我功能*/
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true);      //自动生成记住我表
        return jdbcTokenRepository;
    }


    /**
     * 自定义登录校验
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //获取用户名和密码
        auth.userDetailsService(myUserDetailsService);
    }

    /**
     * 注入加密对象
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder  passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 完成security设置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.formLogin()                                                //自定义自己编写的登录页面
                .loginPage("/login.html")                                   //登录页面地址
                .loginProcessingUrl("/login")                          //登录访问路径-security已经处理了
                .successHandler(localAuthenticationSuccessHandler) // 登录成功
                .failureHandler(localAuthenticationFailureHandler) // 登录失败
                .and().authorizeRequests()
                .antMatchers(
                        "/css/**",
                        "/*.jpg",
                        "/*.jpeg",
                        "/*.png",
                        "/component/**",
                        "/lib/**",
                        "/core/**",
                        "/img/**",
                        "/sys-dict-item/findByDictCode/**",
                        "/growing-environment/findList/**",
                        "/nutrition/findList/**",
                        "/spot-check/findList/**",
                        "/druid/**",
                        "/login.html",
                        "/registered.html",
                        "/sys-user/registered",
                        "/roots.html",
                        "/product-example/get-by-id/**",
                        "/product-example/findTitlePriceList",
                        "/login",
                        "/sys-login/**",
                        "/websocket/**"
                ).permitAll()       //定义不需要认证的路径
                /*多角色权限控制-用逗号隔开*/
//                .antMatchers("/sys-user/**").hasAnyAuthority("user")
                .anyRequest().authenticated()
                .and().rememberMe().tokenRepository(persistentTokenRepository())        //实现记住我
                .tokenValiditySeconds(60)                                               //设置记住我有效时长
                .userDetailsService(myUserDetailsService)                                 //配置查询的service
                .rememberMeParameter("rememberMe")                                      //配置记住我参数
                .rememberMeCookieName("rememberMe-cookie")                             //cookie名称
                .and().csrf().disable();
        http.exceptionHandling().accessDeniedHandler(localAccessDeniedHandler); // 无权访问 JSON 格式的数据
        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.html").permitAll();        //设置退出登录的连接，以及退出成功推到那个页面
//                .logoutSuccessHandler(localLogoutSuccessHandler);   //退出成功调用的类

        /*解决iframe页面无法访问问题*/
        http.headers()
                .frameOptions().sameOrigin()
                .httpStrictTransportSecurity().disable();
        http.addFilterBefore(verifyCodeFilter, UsernamePasswordAuthenticationFilter.class); //定义验证码拦截器
    }
}