package ru.itis.config.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by Андрей on 27.05.2017.
 */
@Configuration
@ComponentScan("java")
@PropertySource("java/db.properties")

public class AppConfig {

    @Autowired
    Environment environment;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.name"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        dataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
        return dataSource;
    }
}
