package com.wq.member.service.impl;

import com.wq.member.dao.UserRolePermissionDao;
import com.wq.member.mapper.UserMapper;
import com.wq.member.model.Permission;
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
    @Autowired
    private UserRolePermissionDao UserRolePermissionDao;

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
        // TODO 账号是否存在
        // if (user == null) {
        //     throw new UsernameNotFoundException("该用户不存在");
        // }
        // TODO 密码是否正确
         return userMapper.selectByPrimaryKey(1);
    }

    @Override
    public List<Permission> findPermissions(Long id) {
        return UserRolePermissionDao.getPermissionList(id);
    }
}
