package com.czy.zhongchou.util;

import com.czy.zhongchou.dao.AccountDAO;
import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.service.AccountService;

import java.sql.SQLException;
import java.util.Random;
//������
public class DealWithCardId {
    //��ȡ���������
    public static String createCardId() throws SQLException, ClassNotFoundException {
        long seed = System.currentTimeMillis();
        // ʹ�����Ӵ��������������
        Random random = new Random(seed);

        // ���ɰ�λ���������
        int randomNum =random.nextInt(90000000) + 10000000;
        String randomNum1=String.valueOf(randomNum);
        return randomNum1;
    }

}
