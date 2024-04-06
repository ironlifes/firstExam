package com.czy.zhongchou.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

//工具类
public class DBUtill {

    public static void main(String[] args) {

    }
    public static Connection getConn() throws Exception {
        Properties properties=new Properties();
        properties.load(new FileInputStream("kaohe/src/druid.properties"));
        DataSource dataSource= DruidDataSourceFactory.createDataSource(properties);
        Connection conn=dataSource.getConnection();
        return conn;
    }

    public static void close(ResultSet rs,PreparedStatement pstmt, Connection conn) throws SQLException {
        if(rs!=null){
            rs.close();
        }
        if(pstmt!=null){
            pstmt.close();
        }
        if(conn!=null){
            conn.close();
        }
    }

    public static void close(PreparedStatement pstmt,Connection conn) throws SQLException {
        if(pstmt!=null){
            pstmt.close();
        }
        if(conn!=null){
            conn.close();
        }
    }

    public static void close(Connection conn) throws SQLException {
        if(conn!=null){
            conn.close();
        }
    }
//    private static String URL="jdbc:mysql://localhost:3306/kaohe";
//    private static String NAME="root";
//    private static String PASSWORD="123456";
//
//    //注册驱动，获取连接
//    public static Connection getConn() throws ClassNotFoundException, SQLException {
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
//        return conn;
//    }
//
//    //释放资源
//    public static void close(PreparedStatement pstmt, Connection conn) throws SQLException {
//        if(pstmt==null){
//            pstmt.close();
//            if(conn==null){
//                conn.close();
//            }
//        }
//    }
//
//    public static void closeConn(Connection conn) throws SQLException {
//        if(conn==null){
//            conn.close();
//        }
//    }
}
