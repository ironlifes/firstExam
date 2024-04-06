package com.czy.zhongchou.util;

import com.czy.zhongchou.dao.AccountDAO;
import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.service.AccountService;

import java.sql.SQLException;
import java.util.Random;
//工具类
public class DealWithCardId {
    //获取随机数返回
    public static String createCardId() throws SQLException, ClassNotFoundException {
        long seed = System.currentTimeMillis();
        // 使用种子创建随机数生成器
        Random random = new Random(seed);

        // 生成八位随机正整数
        int randomNum =random.nextInt(90000000) + 10000000;
        String randomNum1=String.valueOf(randomNum);
        return randomNum1;
    }

}
