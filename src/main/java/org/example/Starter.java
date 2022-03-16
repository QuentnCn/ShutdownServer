package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class Starter {
    static Logger logger = LoggerFactory.getLogger(Starter.class);
    public static void main( String[] args ) {
        logger.info("服务器启动...");
        SpringApplication.run(Starter.class);
    }
}
