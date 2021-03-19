package com.linqibin.springsecuritystudy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // 指定登录页面
                .loginPage("/loginPage.html")
                // 登录处理地址, 与登录表单上action地址相对应, 去执行UserServiceImpl
                .loginProcessingUrl("/login")
                // 登录成功后的跳转地址
                .successForwardUrl("/loginSuccess")
                //.successHandler(new MySuccessHandler("http://www.baidu.com"));
                // 登录失败请求转发
                .failureForwardUrl("/loginFailure");

        http.authorizeRequests()
                // 放行登录页面
                //.antMatchers("/loginPage.html", "/error.html").permitAll()
                .antMatchers("/loginPage.html", "/error.html").access("permitAll")
                // 指定角色abc才能访问
                //.antMatchers("/index.html").hasRole("abc")
                //.antMatchers("/index.html")
                //.access("@myAccessServiceImpl.hasUriPermission(request, authentication)")
                // 所有请求都走自定义的权限判断,
                .anyRequest().authenticated();

        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);

        http.rememberMe()
                // 设置失效时间
                .tokenValiditySeconds(120)
                // 指定登录逻辑对象
                .userDetailsService(userDetailsService)
                // 指定持久层对象
                .tokenRepository(persistentTokenRepository);


        // http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
