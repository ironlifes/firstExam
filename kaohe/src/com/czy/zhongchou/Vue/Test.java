package com.czy.zhongchou.Vue;

import java.sql.SQLException;

//≤‚ ‘¿‡
public class Test {
    public static void main(String[] args){
        Show show=new Show();
        try {
            show.start();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
