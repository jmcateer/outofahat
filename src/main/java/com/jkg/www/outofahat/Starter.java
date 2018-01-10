package com.jkg.www.outofahat;

import org.springframework.boot.ResourceBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.DefaultResourceLoader;

@SpringBootApplication
@ComponentScan("com.jkg.www.outofahat")
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class Starter extends SpringBootServletInitializer {

    public static void main(String... args) {
        SpringApplication.run(Starter.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder
                .banner(new ResourceBanner(new DefaultResourceLoader().getResource("banner.text")))
                .sources(Starter.class);
        return builder;
    }
}
