package com.wq.member.config;

import com.wq.member.security.JwtAuthenticationTokenFilter;
import com.wq.member.security.RestAuthenticationEntryPoint;
import com.wq.member.security.RestfulAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    // 请求/doLogin时用到的
    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
    // 登录后对所请求的接口进行token验证
    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter(){
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 使用自定义登录身份认证组件
//        auth.authenticationProvider(new JwtAuthenticationProvider(userDetailsService));
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder());
    }
    // 加密方式
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .cors().and()
            .csrf().disable()  // 由于使用的是JWT，我们这里不需要csrf
            .sessionManagement()// 基于token，所以不需要session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            // .formLogin()
            // .loginPage("/login1") // 如果发送的请求需要权限403或未登录401，会跳转到这里的/login1让你登录，所以/login1里面可以写要登录的东西返回给前端
            // // .loginProcessingUrl("/doLogin")
            // .and()
            .authorizeRequests() //授权请求
            // 跨域预检的options请求
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // .antMatchers("/**").permitAll()
            // 不设置的话，会一直请求这个请求
            // .antMatchers("/login1").permitAll()
            // 对登录允许匿名访问
            // .antMatchers("/user/doLogin").permitAll()
            .antMatchers("/user/verifyCode").permitAll()
            // 经过反复试验，这样 /user/register 就可以不受认证
            // .antMatchers("/user/register").permitAll()

            .anyRequest()// 除上面外的所有请求全部需要鉴权认证
            .authenticated();

        // 可以有两种登录方式，一种是请求/doLogin，一种是loginFilter


        // 禁用缓存
        http.headers().cacheControl();
        // 登录后对所请求的接口进行token验证
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        // 添加自定义未授权和未登录结果返回
        // http.exceptionHandling()
                    // 403 未授权
        //         .accessDeniedHandler(restfulAccessDeniedHandler)
                    // 401 未登录
        //         .authenticationEntryPoint(restAuthenticationEntryPoint);

    }

    // web相关的静态东西
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/css/**",
                "/js/**",
                "/index.html",
                "/img/**",
                "/fonts/**",
                "/favicon.ico",
                "/verifyCode",
                "/user/doLogin",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/v2/api-docs",
                "/webjars/**"
                );
    }
    // swagger ui相关
    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    
}