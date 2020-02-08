package com.mzx.rbacdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.mzx.rbacdemo.dao")
public class RbacdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RbacdemoApplication.class, args);
    }

}
