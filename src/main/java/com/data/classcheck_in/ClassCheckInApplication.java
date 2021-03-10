package com.data.classcheck_in;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.data.classcheck_in.mapper")
@ServletComponentScan
public class ClassCheckInApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassCheckInApplication.class, args);
    }

}
