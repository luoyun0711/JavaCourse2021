package com.luoyun.course.spring;

import com.luoyun.course.spring.bean_assembly.ConfigurationAssembly;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * WebApplication
 *
 * @author luoyun
 * @data 2021/10/24
 */
@SpringBootApplication
@Import(ConfigurationAssembly.class)
@ImportResource("classpath:spring-bean.xml")
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
