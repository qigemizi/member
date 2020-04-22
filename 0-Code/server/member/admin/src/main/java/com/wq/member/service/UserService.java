package com.wq.member.service;

import com.wq.member.model.User;

import java.util.Set;

public interface UserService {
    /**
     * 注册使用者
     */
    void register(User user);

    void userExcel();

    User findByUsername(String username);

    Set<String> findPermissions(String username);
}
