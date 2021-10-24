package com.luoyun.course.spring.jdbc;

import ch.qos.logback.core.db.ConnectionSource;

import java.sql.*;

/**
 * JdbcDemo
 *
 * @author luoyun
 * @data 2021/10/24
 */
public class JdbcDemo {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&useSSL=false";
            String user = "root";
            String password = "123456";
            connection = DriverManager.getConnection(url,user,password);
            String sql = "select * from test_student where 1=1";
            prepareStatement = connection.prepareStatement(sql);
            resultSet = prepareStatement.executeQuery(sql);
            //如果有数据，rs.next()返回true
            while(resultSet.next()) {
                System.out.println(resultSet.getString("id"));
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
