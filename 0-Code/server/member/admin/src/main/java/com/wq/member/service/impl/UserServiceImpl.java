package com.wq.member.service.impl;

import com.wq.member.mapper.UserMapper;
import com.wq.member.model.User;
import com.wq.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        
        userMapper.insert(user);

    }
}
