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
//                .mvcMatchers( "/").permitAll()
//                .mvcMatchers(HttpMethod.GET, "/api/v1/professors/new").hasAuthority("SCOPE_admin")
//                .mvcMatchers(HttpMethod.GET, "/api/v1/**").authenticated()
//                .mvcMatchers(HttpMethod.POST, "/api/v1/**").hasAuthority("SCOPE_admin")
//                .mvcMatchers(HttpMethod.PUT, "/api/v1/**").hasAuthority("SCOPE_admin")
//                .mvcMatchers(HttpMethod.DELETE, "/api/v1/**").hasAuthority("SCOPE_admin")
                .anyRequest().permitAll().and().csrf().disable()
                .oauth2Login();
    }
}