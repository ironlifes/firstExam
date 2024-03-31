package com.czy.zhongchou.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.cj.jdbc.Driver;
//������
public class DBUtill {

    private static String URL="jdbc:mysql://localhost:3306/kaohe";
    private static String NAME="root";
    private static String PASSWORD="123456";

    //ע����������ȡ����
    public static Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        return conn;
    }

    //�ͷ���Դ
    public static void close(PreparedStatement pstmt, Connection conn) throws SQLException {
        if(pstmt==null){
            pstmt.close();
            if(conn==null){
                conn.close();
            }
        }
    }

    public static void closeConn(Connection conn) throws SQLException {
        if(conn==null){
            conn.close();
        }
    }
}
