package com.wq.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Setter
@ToString
public class MemberParam {
    @ApiModelProperty(value = "会员名", required = true)
    @NotEmpty(message = "会员名不能为空")
    private String name;

    @ApiModelProperty(value = "电话", required = true)
    @NotEmpty(message = "电话不能为空")
    private String phone;

    @ApiModelProperty(value = "注册日期")
    private Date registerDate;
}
