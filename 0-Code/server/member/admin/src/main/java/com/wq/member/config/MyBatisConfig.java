package com.wq.member.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis配置类
 * Created by macro on 2019/4/8.
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.wq.member.mapper","com.wq.member.dao"})
public class MyBatisConfig {
    //扫描，这样自动注入就不会有个红线报错
    @Bean
    public void sout(){
        System.out.println("进入sout方法了");
    }
}
