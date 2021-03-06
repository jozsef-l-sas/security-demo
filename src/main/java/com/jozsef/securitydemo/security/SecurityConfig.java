package com.jozsef.securitydemo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .mvcMatchers("/login").permitAll()
                    .mvcMatchers("/support/admin").hasRole("ADMIN")
                    .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/portfolio", true)
                .and().logout().logoutUrl("/api/logout");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/css/**", "/webjars/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("victoria").password("{noop}password").roles("USER");
        auth.inMemoryAuthentication().withUser("dave").password("{noop}password").roles("USER");
    }
}
