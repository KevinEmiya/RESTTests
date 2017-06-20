package com.ky.tests.main;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.ky.tests"})
public class ClientMain
{
    public static void main(String[] args)
    {
        SpringApplication application = new SpringApplication(ClientMain.class);
        Map<String, Object> defaultMap = new HashMap<String, Object>();
        defaultMap.put("spring.config.location", "classpath:conf/");
        defaultMap.put("spring.config.name", "kyclient");
        application.setDefaultProperties(defaultMap);
        application.run(args);
    }
}