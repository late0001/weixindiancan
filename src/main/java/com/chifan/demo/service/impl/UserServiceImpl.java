package com.chifan.demo.service.impl;

import com.chifan.demo.mapper.UserMapper;
import com.chifan.demo.pojo.User;
import com.chifan.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;
    @Override
    public User findByAge(int age) {
        return mapper.findByAge(age);
    }
}
