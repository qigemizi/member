package com.wq.member.controller;

import com.wq.member.common.api.CommonResult;
import com.wq.member.common.api.ResultCode;
import com.wq.member.util.VerifyCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
public class LoginController {
    @GetMapping("/login")
    public CommonResult login() {
        return CommonResult.failed(ResultCode.FAILED, "尚未登录，请登录!");
    }

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
}