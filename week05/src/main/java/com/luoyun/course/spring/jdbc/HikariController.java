package com.luoyun.course.spring.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * HikariController
 *
 * @author luoyun
 * @data 2021/10/24
 */
@RestController
public class HikariController {

    @Autowired
    private DataSource datasource;

    private static Logger logger = LoggerFactory.getLogger(HikariController.class);

    @RequestMapping("/test/hikari")
    public void testHikari() {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            connection = datasource.getConnection();
            String sql = "select * from test_student where 1=1";
            prepareStatement = connection.prepareStatement(sql);
            resultSet = prepareStatement.executeQuery(sql);
            //如果有数据，rs.next()返回true
            while(resultSet.next()) {
                logger.info("id:" + resultSet.getString("id"));
            }
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (prepareStatement != null) {
                    prepareStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
