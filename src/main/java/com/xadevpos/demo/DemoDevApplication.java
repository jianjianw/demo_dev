package com.xadevpos.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.xadevpos.demo.mapper")
@ServletComponentScan("com.xadevpos.demo.aop.filter")
public class DemoDevApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoDevApplication.class, args);
    }

}
