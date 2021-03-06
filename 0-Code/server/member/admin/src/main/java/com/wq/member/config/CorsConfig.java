package com.wq.member.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * http://www.ruanyifeng.com/blog/2016/04/cors.html
 * CORS是一个W3C标准，全称是"跨域资源共享"（Cross-origin resource sharing）。
 * 它允许浏览器向跨源服务器，发出XMLHttpRequest请求，从而克服了AJAX只能同源使用的限制。同源策略是由Netscape提出的一个著名的安全策略。
 *
 * https://macrozheng.github.io/mall-learning/#/technology/springboot_cors
 * 当一个资源去访问另一个不同域名或者同域名不同端口的资源时，就会发出跨域请求。
 * 一次完整的跨域请求：先发起一次OPTIONS请求进行预检，再发起真实的跨域请求
 *
 * 今天发现一个问题：
 * 前端发出一个请求之后，该请求发现需要登录，就重定向到登录请求
 * 这时会发生跨域的问题
 * 本类就是解决这个问题
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("进入addCorsMappings方法了，解决跨域问题");
        registry.addMapping("/**")    // 允许跨域访问的路径，/**表示本应用的所有方法都会去处理跨域请求
        .allowedOrigins("*")    // 允许跨域访问的源
        .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")    // 允许通过的请求方法
        .maxAge(168000)    // 预检间隔时间
        .allowedHeaders("*")  // 允许通过的请求头部设置
        .allowCredentials(true);    // 是否发送cookie
        // 跨域问题解决了，但是也有潜在的威胁存在，常见的就是CSRF（Cross-site request forgery）跨站请求伪造。
        // 跨站请求伪造也被称为one-click attack 或者 session riding，通常缩写为CSRF或者XSRF
        // 基于此，浏览器在实际操作中，会对请求进行分类，分为简单请求，预先请求，带凭证的请求等，
        // 预先请求会首先发送一个options探测请求，和浏览器进行协商是否接受请求。
        // 默认情况下跨域请求是不需要凭证的，但是服务端可以配置要求客户端提供凭证，这样就可以有效避免csrf攻击。
    }
}