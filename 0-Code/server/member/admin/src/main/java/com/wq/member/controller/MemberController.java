package com.wq.member.controller;

import com.wq.member.common.api.CommonResult;
import com.wq.member.dto.MemberParam;
import com.wq.member.model.Member;
import com.wq.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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

    @ApiOperation(value = "添加会员")
    // @RequestMapping(value = "/createMember", method = RequestMethod.POST)
    @PostMapping("/createMember")
    @ResponseBody
    public CommonResult createMember(@RequestBody MemberParam memberParam, BindingResult result) {
        System.out.println("进入createMember方法了");
        log.debug("memberParam:{}", memberParam.toString());
        log.debug("result:{}", result.toString());

        // if (result.hasErrors()) {
        //     return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        // }
        CommonResult commonResult;
        int count = memberService.createMember(memberParam);
        if (count == 1) {
            commonResult = CommonResult.success(memberParam);
            log.debug("createBrand success:{}", memberParam);
        } else {
            commonResult = CommonResult.failed("添加会员失败");
            log.debug("createBrand failed:{}", memberParam);
        }
        return commonResult;
    }

    @ApiOperation(value = "更新会员信息")
    @RequestMapping(value = "/updateMember/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateMember(@PathVariable("id") Long id, @Validated @RequestBody MemberParam memberParam,BindingResult result) {
        log.debug("memberParam:{}", memberParam.toString());
        log.debug("result:{}", result.toString());
        if(result.hasErrors()){
            return CommonResult.validateFailed(result.getFieldError().getDefaultMessage());
        }
        CommonResult commonResult;
        int count = memberService.updateMember(id, memberParam);
        if (count == 1) {
            commonResult = CommonResult.success(memberParam);
            log.debug("updateBrand success:{}", memberParam);
        } else {
            commonResult = CommonResult.failed("操作失败");
            log.debug("updateBrand failed:{}", memberParam);
        }
        return commonResult;
    }
}
