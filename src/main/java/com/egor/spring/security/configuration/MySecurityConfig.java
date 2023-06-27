package com.egor.spring.security.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
//        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();                 // в БД создали этих юзеров и пароли, поэтому тут не нужны
//        auth.inMemoryAuthentication().withUser(userBuilder.username("egor").password("egor").roles("EMPLOYEE"))
//        .withUser(userBuilder.username("vika").password("vika").roles("Manager"))
//        .withUser(userBuilder.username("fedor").password("fedor").roles("HR", "Manager"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests().antMatchers("/").hasAnyRole("MANAGER","HR", "EMPLOYEE")
               .antMatchers("/hr_info").hasRole("HR")
               .antMatchers("/manager_info/**").hasRole("MANAGER").// звездочки- менеджеру достумны все view с началом /manager_info
               and().formLogin().permitAll();
    }
}
