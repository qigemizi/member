package com.wq.member.service.impl;

import com.wq.member.mapper.UserMapper;
import com.wq.member.model.User;
import com.wq.member.model.UserExample;
import com.wq.member.service.UserService;
import com.wq.member.util.CreatExcel;
import com.wq.member.util.MapAndBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        
        userMapper.insert(user);

    }

    @Override
    public void userExcel() {
        List<User> userList = userMapper.selectByExample(new UserExample());
        List<Map<String, Object>> mapList = MapAndBeanUtil.beansToMap(userList);
        try {
            CreatExcel.writeExcel(mapList,"/Users/wq/Java-Platform");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public User findByUsername(String username) {
         return userMapper.selectByPrimaryKey(1);
    }

    @Override
    public Set<String> findPermissions(String username) {
        return null;
    }
}
