package com.densoft.springsecuritydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //tell spring security to use JDBC authentication with our data source
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests() //restrict access based on the HttpServletRequest
                .antMatchers("/assets/**").permitAll() //allow all requests to the assets folder
                .antMatchers("/").hasRole("EMPLOYEE")
                .antMatchers("/leaders/**").hasRole("MANAGER") //restricted to manager
                .antMatchers("/systems/**").hasRole("ADMIN")//restricted to admin
                .and()
                .formLogin() //customising the login form process
                .loginPage("/showMyLoginPage") //show the custom login page
                .loginProcessingUrl("/authenticateUser") //to process the login
                .permitAll()//allow anyone to see the login page
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied"); //allow anyone to see the logout page
    }


}
