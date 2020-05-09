package com.wq.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
public class ConsumptionParam {
    @ApiModelProperty(value = "会员id", required = true)
    @NotEmpty(message = "会员id不能为空")
    private Long memberId;

    @ApiModelProperty(value = "本次消费额度", required = true)
    @NotEmpty(message = "本次消费额度不能为空")
    private String quota;
}
