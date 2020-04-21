package com.wq.member.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * http://www.ruanyifeng.com/blog/2016/04/cors.html
 * CORS是一个W3C标准，全称是"跨域资源共享"（Cross-origin resource sharing）。
 * 它允许浏览器向跨源服务器，发出XMLHttpRequest请求，从而克服了AJAX只能同源使用的限制。
 *
 * 今天发现一个问题：
 * 我发出一个请求之后，该请求发现需要登录，就重定向到登录请求
 * 这时会发生跨域的问题
 * 本类就是解决这个问题
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("进入addCorsMappings方法了");
        registry.addMapping("/**")    // 允许跨域访问的路径
        .allowedOrigins("*")    // 允许跨域访问的源
        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")    // 允许请求方法
        .maxAge(168000)    // 预检间隔时间
        .allowedHeaders("*")  // 允许头部设置
        .allowCredentials(true);    // 是否发送cookie
    }
}