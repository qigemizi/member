package com.wq.member.service;

import com.wq.member.model.User;

public interface UserService {
    /**
     * 注册使用者
     */
    void register(User user);

    void userExcel();
}
