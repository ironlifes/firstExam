package com.czy.zhongchou.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

//工具类
public class DruidUtil {
    private static DruidDataSource dataSource;

    private static final String URL = "jdbc:mysql://localhost:3306/kaohe";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    //初始化连接池
    static {
        //连接池配置
        dataSource=new DruidDataSource();
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);

        dataSource.setInitialSize(5);
        dataSource.setMaxActive(10);
        dataSource.setMaxWait(3000);
    }
    public static Connection getConn() throws Exception {
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
}
