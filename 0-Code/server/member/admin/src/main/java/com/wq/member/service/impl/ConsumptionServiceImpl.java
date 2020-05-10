package com.wq.member.service.impl;

import cn.hutool.core.convert.Convert;
import com.wq.member.dto.ConsumptionParam;
import com.wq.member.mapper.ConsumptionMapper;
import com.wq.member.model.Consumption;
import com.wq.member.model.ConsumptionExample;
import com.wq.member.service.ConsumptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ConsumptionServiceImpl implements ConsumptionService {

    @Autowired
    private ConsumptionMapper consumptionMapper;

    @Override
    public List<Consumption> getConsumption(Long memberId) {
        ConsumptionExample example = new ConsumptionExample();
        ConsumptionExample.Criteria criteria = example.createCriteria();
        criteria.andMemberIdEqualTo(Convert.toInt(memberId));
        // TODO 设置多表联查，把memberId替换成name
        // TODO 格式化日期时间的格式
        return consumptionMapper.selectByExample(example);
    }

    @Override
    public int putConsumption(ConsumptionParam consumptionParam) {
        Consumption consumption = new Consumption();
        consumption.setConsumptionDate(new Date());
        consumption.setConsumptionQuota(consumptionParam.getQuota());
        consumption.setMemberId(Convert.toInt(consumptionParam.getMemberId()));
        return consumptionMapper.insert(consumption);
    }
}
