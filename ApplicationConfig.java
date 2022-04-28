package com.demo.jdbc;

import java.net.URL;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


@Configuration
@ComponentScan(basePackages = "com.demo")
@PropertySource(value = { "classpath:application.properties" })
public class ApplicationConfig {

   @Autowired
   private Environment env;

   @Bean
	DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(env.getRequiredProperty("jdbc.url"));
		driverManagerDataSource.setUsername(env.getRequiredProperty("jdbc.username"));
		driverManagerDataSource.setPassword(env.getRequiredProperty("jdbc.password"));
		driverManagerDataSource.setDriverClassName(env.getRequiredProperty("jdbc.driverClassName"));
		return driverManagerDataSource;
	}
   @Bean
   public JdbcTemplate jdbcTemplate(DataSource dataSource) {
       JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
       jdbcTemplate.setResultsMapCaseInsensitive(true);
       return jdbcTemplate;
   }

}