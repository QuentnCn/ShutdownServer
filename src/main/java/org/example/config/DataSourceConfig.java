package org.example.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

@Configuration
public class DataSourceConfig {
	Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

	@Bean
	public ComboPooledDataSource comboPooledDataSource() {
		ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
		try {
			comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/shutdown?useUnicode=true&characterEncoding=utf-8&useSSL=false");
			comboPooledDataSource.setUser("root");
			comboPooledDataSource.setPassword("root");
			comboPooledDataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
		} catch (PropertyVetoException e) {
			logger.info(e.getMessage());
		}
		return comboPooledDataSource;
	}
}
