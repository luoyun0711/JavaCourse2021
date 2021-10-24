package com.luoyun.course.spring.starter.meta;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Component("student100")
@ConfigurationProperties(
        prefix = "spring.student"
)
public class Student {

    private int id;

    private String name;

}
