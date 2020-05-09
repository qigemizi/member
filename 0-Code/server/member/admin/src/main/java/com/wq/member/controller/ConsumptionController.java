package com.wq.member.controller;

import com.wq.member.common.api.CommonResult;
import com.wq.member.dto.ConsumptionParam;
import com.wq.member.dto.MemberParam;
import com.wq.member.model.Consumption;
import com.wq.member.model.Member;
import com.wq.member.service.ConsumptionService;
import com.wq.member.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(value = "consumption")
@Controller
@RequestMapping(value = "/consumption")
public class ConsumptionController {

    @Autowired
    private ConsumptionService consumptionService;

    @ApiOperation(value = "查询会员信息消费详情")
    @RequestMapping(value = "/{memberId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<?> getConsumption(@PathVariable("memberId") Long memberId) {
        return CommonResult.success(consumptionService.getConsumption(memberId));
    }

    @ApiOperation(value = "会员本次消费")
    @RequestMapping(value = "/quota", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<?> putConsumption(@RequestBody ConsumptionParam consumptionParam){
        return CommonResult.success(consumptionService.putConsumption(consumptionParam));
    }

}
