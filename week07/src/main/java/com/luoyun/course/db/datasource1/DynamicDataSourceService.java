package com.luoyun.course.db.datasource1;

import com.luoyun.course.db.DynamicDataSourceController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * DynamicDataSourceService
 *
 * @author luoyun
 * @data 2021/11/8
 */
@Component
public class DynamicDataSourceService {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceController.class);

    @Resource
    private DynamicDataSource dynamicDataSource;

    @UseDataSource(name = "f1")
    public void getTest1Data() {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dynamicDataSource.getConnection();
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

    @UseDataSource(name = "s2")
    public void getTest2Data() {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            connection = dynamicDataSource.getConnection();
            String sql = "select * from t_user where 1=1";
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
