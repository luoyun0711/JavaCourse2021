package com.luoyun.course.spring.starter.meta;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Data
@Component("class1")
public class Klass { 

    @Resource
    private List<Student> students;
    
    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
