package com.luoyun.course.spring.starter;

import com.luoyun.course.spring.starter.meta.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SchoolStarterTest
 *
 * @author luoyun
 * @data 2021/10/24
 */
@RestController
public class SchoolStarterTest {

    @Autowired
    private School school;

    @RequestMapping("/test/school")
    public void testSchool() {
        school.ding();
    }


}
