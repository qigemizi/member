package com.wq.member.security;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.convert.Convert;
import com.wq.member.model.Permission;
import com.wq.member.model.User;
import com.wq.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * 用户登录认证信息查询
 * @author Louis
 * @date Jun 29, 2019
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("该用户不存在");
        }
        // 用户权限列表，根据用户拥有的权限标识与如 @PreAuthorize("hasAuthority('sys:menu:view')") 标注的接口对比，决定是否可以调用接口
        List<Permission> permissions = userService.findPermissions(Convert.toLong(user.getId()));
        List<GrantedAuthority> grantedAuthorities =
                permissions.stream().filter(permission -> permission.getValue()!=null).map(permission -> new SimpleGrantedAuthority(permission.getValue())) //getValue()=member:list:read
                .collect(Collectors.toList());
        System.out.println("获取到的权限列表="+permissions.toString());

        return new JwtUserDetails(username, passwordEncoder.encode(user.getPassword()), grantedAuthorities);

    }
}