package com.czy.zhongchou.Vue;

import com.czy.zhongchou.entity.AccountDO;

import java.sql.SQLException;

//≤‚ ‘¿‡
public class Main {
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
