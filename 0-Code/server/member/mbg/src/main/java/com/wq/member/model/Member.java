package com.wq.member.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Member implements Serializable {
    private Integer id;

    private String name;

    @ApiModelProperty(value = "注册日期")
    private Date registerData;

    @ApiModelProperty(value = "总消费额")
    private String totalConsumption;

    @ApiModelProperty(value = "手机号")
    private String phone;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegisterData() {
        return registerData;
    }

    public void setRegisterData(Date registerData) {
        this.registerData = registerData;
    }

    public String getTotalConsumption() {
        return totalConsumption;
    }

    public void setTotalConsumption(String totalConsumption) {
        this.totalConsumption = totalConsumption;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", registerData=").append(registerData);
        sb.append(", totalConsumption=").append(totalConsumption);
        sb.append(", phone=").append(phone);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}