package com.czy.zhongchou.Vue;

import com.czy.zhongchou.entity.AccountDO;

import java.sql.SQLException;

//��ʼ
public class Main {
    public static void main(String[] args) {
        Show show = new Show();
        try {
            show.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
