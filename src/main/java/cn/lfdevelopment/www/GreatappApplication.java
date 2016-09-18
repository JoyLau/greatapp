/*******************************************************************************
 * Copyright (c) 2016 by LiuFa. All rights reserved
 ******************************************************************************/

package cn.lfdevelopment.www;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by LiuFa on 2016/8/17.
 * cn.lfdevelopment.www
 * GreatappApplication
 */
@SpringBootApplication// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class GreatappApplication extends SpringBootServletInitializer {

    private static Logger _logger = LoggerFactory.getLogger(GreatappApplication.class);

    public static void main(String[] args) {
        _logger.info("spring boot application <main> is starting.....");
        SpringApplication.run(GreatappApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        _logger.info("spring boot application <Web container> is starting.....");
        return application.sources(GreatappApplication.class);
    }
}