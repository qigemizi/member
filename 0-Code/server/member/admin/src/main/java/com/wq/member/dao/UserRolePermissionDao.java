package com.wq.member.dao;


import com.wq.member.model.Permission;
import com.wq.member.model.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户与角色管理自定义Dao
 * Created by macro on 2018/10/8.
 */
public interface UserRolePermissionDao {

    /**
     * 根据用户id查询他有什么权限
     */
    List<Permission> getPermissionList(@Param("Id") Long Id);

    /**
     * 根据用户id查询他有什么权限
     */
    List<Resource> getResourceList(@Param("userId") Long userId);
}
