package com.arcsapt.sms.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan(basePackages="com.arcsapt.sms")
@ImportResource(value={"classpath:spring-security.xml"})
public class AppConfig {

}
