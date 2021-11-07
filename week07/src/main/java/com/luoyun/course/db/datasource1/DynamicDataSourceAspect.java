package com.luoyun.course.db.datasource1;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * DynamicDataSourceAspect
 *
 * @author luoyun
 * @data 2021/11/7
 */
@Aspect
@Component
public class DynamicDataSourceAspect implements Ordered {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Override
    public int getOrder() {
        return 1;
    }

    @Pointcut("@annotation(com.luoyun.course.db.datasource1.UseDataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        UseDataSource ds = method.getAnnotation(UseDataSource.class);
        if (ds == null) {
            DynamicDataSource.switchDataSource("f1");
            logger.info("current dataSource is f1");
        } else {
            DynamicDataSource.switchDataSource(ds.name());
            logger.debug("current datasource is:{}",ds.name());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSource.clear();
            logger.debug("clean datasource");
        }
    }
}
