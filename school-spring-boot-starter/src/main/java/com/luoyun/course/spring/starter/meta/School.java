package com.luoyun.course.spring.starter.meta;



import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Data
@Component
public class School{

    @Autowired
    Klass class1;
    
    @Resource(name = "student100")
    Student student100;

    private static Logger logger = LoggerFactory.getLogger(School.class);

    public void ding(){
        logger.info("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);
    }
    
}
