package com.example.ly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.ly.mapper")
//这里是扫包，mapper包
public class LyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LyApplication.class, args);
    }

}
