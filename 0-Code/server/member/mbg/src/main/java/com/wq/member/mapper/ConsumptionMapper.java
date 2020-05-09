package com.wq.member.mapper;

import com.wq.member.model.Consumption;
import com.wq.member.model.ConsumptionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConsumptionMapper {
    long countByExample(ConsumptionExample example);

    int deleteByExample(ConsumptionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Consumption record);

    int insertSelective(Consumption record);

    List<Consumption> selectByExample(ConsumptionExample example);

    Consumption selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Consumption record, @Param("example") ConsumptionExample example);

    int updateByExample(@Param("record") Consumption record, @Param("example") ConsumptionExample example);

    int updateByPrimaryKeySelective(Consumption record);

    int updateByPrimaryKey(Consumption record);
}