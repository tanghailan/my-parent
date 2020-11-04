package com.kanavi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author hailan
 * @version 1.0
 * @className MyApplication
 * @description TODO
 * @date 2020-09-07 23:38
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.kanavi.system.mapper"})
@EnableSwagger2
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class,args);
    }

}
