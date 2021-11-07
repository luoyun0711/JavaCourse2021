package com.luoyun.course.db.datasource1;

import java.lang.annotation.*;

/**
 * CurrentDataSource
 * 数据源指定
 * @author luoyun
 * @data 2021/11/7
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseDataSource {

    String name() default "";
}
