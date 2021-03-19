package com.linqibin.springsecuritystudy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linqibin.springsecuritystudy.entity.UserEntity;
import com.linqibin.springsecuritystudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);

        // 根据前端传进来的username查询用户
        UserEntity user = this.userService.getOne(queryWrapper);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }

        System.out.println(user);

        return new User(username, user.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin,ROLE_abc,/insert,/delete"));
    }
}
