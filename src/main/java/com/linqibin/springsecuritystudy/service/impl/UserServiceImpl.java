package com.linqibin.springsecuritystudy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linqibin.springsecuritystudy.dao.UserMapper;
import com.linqibin.springsecuritystudy.entity.UserEntity;
import com.linqibin.springsecuritystudy.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

}
