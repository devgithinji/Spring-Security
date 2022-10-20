package com.densoft.springsecuritydemo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Objects;
import java.util.logging.Logger;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.densoft.springsecuritydemo")
@PropertySource("classpath:persistence-mysql.properties")
public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private Environment env;

    private Logger logger = Logger.getLogger(getClass().getName());

    @Bean
    public DataSource securityDataSource() {
        //create a connection pool
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        //set the jdbc driver
        try {
            comboPooledDataSource.setDriverClass(env.getProperty("jdbc.driver").trim());

        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }

        logger.info(" jdbc url: " + env.getProperty("jdbc.url"));
        //set database connection props
        comboPooledDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        comboPooledDataSource.setUser(env.getProperty("jdbc.user"));
        comboPooledDataSource.setPassword(env.getProperty("jdbc.password"));
        //set connection pool props
        comboPooledDataSource.setInitialPoolSize(getPropertyValue("connection.pool.initialPoolSize"));
        comboPooledDataSource.setMinPoolSize(getPropertyValue("connection.pool.minPoolSize"));
        comboPooledDataSource.setMaxPoolSize(getPropertyValue("connection.pool.maxPoolSize"));
        comboPooledDataSource.setMaxIdleTime(getPropertyValue("connection.pool.maxIdleTime"));

        return comboPooledDataSource;
    }

    private int getPropertyValue(String propName) {
        return Integer.parseInt(Objects.requireNonNull(env.getProperty(propName)));
    }

    //define a bean for the view resolver
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    }
}
