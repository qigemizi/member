package com.wq.member.service;

import com.wq.member.dto.ConsumptionParam;
import com.wq.member.model.Consumption;

import java.util.List;

public interface ConsumptionService {
    List<Consumption> getConsumption(Long memberId);

    int putConsumption(ConsumptionParam consumptionParam);
}
