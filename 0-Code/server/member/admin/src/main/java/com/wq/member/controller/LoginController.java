package com.wq.member.controller;

import com.wq.member.common.api.CommonResult;
import com.wq.member.common.api.ResultCode;
import com.wq.member.dto.UserParam;
import com.wq.member.security.JwtAuthenticationToken;
import com.wq.member.util.JwtTokenUtils;
import com.wq.member.util.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;


//    @GetMapping("/login")
//    public CommonResult login() {
//        return CommonResult.failed(ResultCode.FAILED, "尚未登录，请登录!");
//    }

    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VerifyCodeUtil code = new VerifyCodeUtil();
        System.out.println("code="+code);
        BufferedImage image = code.getImage();
        System.out.println("image="+image);
        String text = code.getText();
        System.out.println("text="+text);
        HttpSession session = request.getSession(true);
        System.out.println("session="+session);
        session.setAttribute("verify_code", text);

        VerifyCodeUtil.output(image, response.getOutputStream());
        System.out.println("response="+response);
    }

    // 还有两个问题没有搞懂
    // 1、这里为啥要是Get请求
    // 2、我写的user/doLogin为啥不生效   好像给密码加了@bean之后，又生效了
    @ResponseBody
    @GetMapping("/login")
    public CommonResult login(@RequestBody UserParam userParam, HttpServletRequest request) {
        System.out.println("前端页面的点击事件传过来了！login.userParam="+userParam.toString());
        String username = userParam.getUsername();
        String password = userParam.getPassword();
        // 自定义认证token对象
        JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(username, password);
        jwtAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // 执行登录认证过程
        Authentication authentication = authenticationManager.authenticate(jwtAuthenticationToken);
        // 认证成功存储认证信息到上下文
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = JwtTokenUtils.generateToken(authentication);
        System.out.println("token="+token);
        // 生成令牌并返回给客户端
        jwtAuthenticationToken.setToken(token);

        return CommonResult.success(token);

    }
}