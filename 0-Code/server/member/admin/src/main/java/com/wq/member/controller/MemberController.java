package com.wq.member.controller;

import com.wq.member.common.api.CommonResult;
import com.wq.member.model.Member;
import com.wq.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "member")
@Controller
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ResponseBody
    @GetMapping("/list")
    public CommonResult memberList(){
        System.out.println("进入memberList方法了");
        List<Member> memberList = memberService.list();
        return CommonResult.success(memberList);
    }

    @ApiOperation(value = "根据编号查询会员信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Member> brand(@PathVariable("id") Long id) {
        return CommonResult.success(memberService.getMember(id));
    }
}
