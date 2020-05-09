package com.wq.member.controller;

import com.wq.member.common.api.CommonResult;
import com.wq.member.model.Consumption;
import com.wq.member.model.Member;
import com.wq.member.service.ConsumptionService;
import com.wq.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Api(value = "consumption")
@Controller
@RequestMapping(value = "/consumption")
public class ConsumptionController {

    @Autowired
    private ConsumptionService consumptionService;

    @ApiOperation(value = "根据编号查询会员信息")
    @RequestMapping(value = "/{memberId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<?> brand(@PathVariable("memberId") Long memberId) {
        return CommonResult.success(consumptionService.getConsumption(memberId));
    }

}
