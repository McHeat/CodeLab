package com.mcheat.code.lab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Created by Administrator on 2017/9/25
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()        //  设置使用HTTP Basic认证
                .and()
                .formLogin()        //  通过表单登录认证
//                    .loginPage("/login").permitAll()
                .and()
                .logout()
//                .logoutUrl("/logout") ·       // 登出
//                .logoutSuccessUrl("/index")
                .and()
                .authorizeRequests()
//                .antMatchers();
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .headers().defaultsDisabled().cacheControl();
        // sessionManagement并发线程控制，需添加HttpSessionEventPublisher监听器
        http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(true);
    }

     // 效果与configure(AuthenticationManagerBuilder auth)等效
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user").password("password").roles("USER", "ACTUATOR").build());
        return manager;
    }

/*       @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    */

    /**
     * 配置UserDetailsService
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
//                .passwordEncoder(new BCryptPasswordEncoder())
        ;
    }

}
