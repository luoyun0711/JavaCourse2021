package com.luoyun.course.spring.starter;


import com.luoyun.course.spring.starter.meta.School;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


/**
 * SchoolAutoConfiguration
 *
 * @author luoyun
 * @data 2021/10/24
 */
@ConditionalOnClass({School.class})
@EnableConfigurationProperties
public class SchoolAutoConfiguration {


}
