package com.linqibin.springsecuritystudy.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MySuccessHandler implements AuthenticationSuccessHandler {

    public final String url;

    public MySuccessHandler(String url) {
        this.url = url;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        // 主体, 即查询到的用户
        User user = (User) authentication.getPrincipal();
        System.out.println(user);
        // 详情信息, IP地址, SessionId
        Object details = authentication.getDetails();
        System.out.println(details);
        System.out.println(authentication.getName());
        httpServletResponse.sendRedirect(this.url);
    }
}
