package com.atguigu.guli.service.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.atguigu.guli"})
public class ServiceEduApp{

    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApp.class, args);
    }
}