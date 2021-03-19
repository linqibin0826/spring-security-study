package com.linqibin.springsecuritystudy.service;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface MyAccessService {

    boolean hasUriPermission(HttpServletRequest request, Authentication authentication);
}
