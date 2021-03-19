package com.linqibin.springsecuritystudy.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    // @Secured("ROLE_abc")
    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping("/loginSuccess")
    public String loginSuccess() {
        return "index";
    }

    @RequestMapping("/loginFailure")
    public String loginFailure() {
        return "redirect:/error.html";
    }


}
