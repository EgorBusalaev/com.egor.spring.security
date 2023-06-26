package com.egor.spring.security.configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
        auth.inMemoryAuthentication().withUser(userBuilder.username("egor").password("egor").roles("EMPLOYEE"))
        .withUser(userBuilder.username("vika").password("vika").roles("Manager"))
        .withUser(userBuilder.username("fedor").password("fedor").roles("HR", "Manager"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests().antMatchers("/").hasAnyRole("Manager","HR", "EMPLOYEE")
               .antMatchers("/hr_info").hasRole("HR")
               .antMatchers("/manager_info/**").hasRole("Manager").// звездочки- менеджеру достумны все view с началом /manager_info
               and().formLogin().permitAll();
    }
}
