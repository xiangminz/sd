package com.jtthink.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Value(value = "${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String name;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;



    @Bean
    public DataSource dataSource(){
        org.apache.tomcat.jdbc.pool.DataSource dataSource=new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(name);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        return dataSource;
    }



}
