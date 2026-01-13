package com.zh.mybatisplus;


import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcTest {
        public static void main(String[] args) {
            String url = "jdbc:mysql://localhost:3306/mybatis_plus_finally?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "123456";
            try {
                // MySQL 8.x 用 com.mysql.cj.jdbc.Driver
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, user, password);
                System.out.println("连接成功！");
                conn.close();
            } catch (Exception e) {
                e.printStackTrace(); // 打印详细错误，定位根因
            }
        }
    }


