package com.cb.security.config;

import com.cb.config.JWTConfig;
import com.cb.security.UserAuthenticationProvider;
import com.cb.security.UserPermissionEvaluator;
import com.cb.security.filter.JWTAuthenticationFilter;
import com.cb.security.handler.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

/**
 * @description: 安全核心配置
 * @author:
 * @create: 2020-11-11 17:51
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*
    无权限处理
*/
    @Autowired
    private UserAccessDeniedHandler userAccessDeniedHandler;

    /*
        w未登录处理
    */
    @Autowired
    private UserNotLoginHandler userNotLoginHandler;

/*
    登陆成功处理
*/
    @Autowired
    private UserLoginSuccessHandler userLoginSuccessHandler;

/*
    登录失败处理
*/
    @Autowired
    private UserLoginFailureHandler userLoginFailureHandler;

/*
    登出处理
*/
    @Autowired
    private UserLogoutSuccessHandler userLogoutSuccessHandler;

/*
    登陆验证
*/
    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

/*
    自定义用户权限处理注解
*/
    @Autowired
    private UserPermissionEvaluator userPermissionEvaluator;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

/*
    注入自定义注解
*/
    @Bean
    public DefaultWebSecurityExpressionHandler securityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler= new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(userPermissionEvaluator);
        return handler;
    }


/*
    登陆验证
*/
    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(userAuthenticationProvider);
    }

/*
    安全权限配置
*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(JWTConfig.antMatchers.split(",")).permitAll()//白名单
                .anyRequest().authenticated()//对其他请求均进行验证
                .and().httpBasic().authenticationEntryPoint(userNotLoginHandler)//针对未登录处理
                .and().formLogin().loginProcessingUrl("/login")
                .successHandler(userLoginSuccessHandler)
                .failureHandler(userLoginFailureHandler)
                .and().logout().logoutUrl("/logout")
                .logoutSuccessHandler(userLogoutSuccessHandler)
                .and().exceptionHandling().accessDeniedHandler(userAccessDeniedHandler)
                .and().cors()
                .and().csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().cacheControl();
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));//添加JWT过滤
    }
}
