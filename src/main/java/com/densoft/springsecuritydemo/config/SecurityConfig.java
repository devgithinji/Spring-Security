package com.densoft.springsecuritydemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.core.userdetails.User.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserBuilder userBuilder = withDefaultPasswordEncoder();

        auth.inMemoryAuthentication()
                .withUser(userBuilder.username("john").password("test123").roles("EMPLOYEE"))
                .withUser(userBuilder.username("mary").password("test123").roles("MANAGER"))
                .withUser(userBuilder.username("susan").password("test123").roles("ADMIN"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //restrict access based on the HttpServletRequest
                .anyRequest().authenticated() //any request coming to the app must be authenticated
                .and()
                .formLogin() //customising the login form process
                .loginPage("/showMyLoginPage") //show the custom login page
                .loginProcessingUrl("/authenticateUser") //to process the login
                .permitAll(); //allow anyone to see the login page
    }
}
