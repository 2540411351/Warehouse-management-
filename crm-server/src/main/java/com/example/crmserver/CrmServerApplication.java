package com.example.crmserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.crmserver.mapper")
public class CrmServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrmServerApplication.class, args);
    }

}
