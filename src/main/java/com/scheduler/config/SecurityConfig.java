package com.scheduler.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers( "/").permitAll()
                .mvcMatchers( "/api/v1/auth/token").permitAll()
                .mvcMatchers(HttpMethod.GET, "/api/v1/**").authenticated()
                .mvcMatchers(HttpMethod.POST, "/api/v1/**").hasAuthority("write:all")
                .mvcMatchers(HttpMethod.PUT, "/api/v1/**").hasAuthority("write:all")
                .mvcMatchers(HttpMethod.DELETE, "/api/v1/**").hasAuthority("write:all")
                .anyRequest().permitAll()
                .and().oauth2Login();
    }
}