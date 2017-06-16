/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lenovo.lic.machine.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author vntrotq
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .and()
                .httpBasic()
                .and().authorizeRequests().antMatchers("/connect").authenticated()
                .and().httpBasic().and().authorizeRequests()
                .antMatchers("/getmachines")
                .permitAll()
                .and().authorizeRequests()
                .antMatchers("/getmachine")
                .permitAll()
                .and().authorizeRequests()
                .antMatchers("/disconnect")
                .permitAll()
                .and().authorizeRequests()
                .antMatchers("/power")
                .permitAll()
                .and().authorizeRequests()
                .antMatchers("/edit")
                .permitAll()
                .and().authorizeRequests()
                .antMatchers("/add")
                .permitAll();                
    }
}
