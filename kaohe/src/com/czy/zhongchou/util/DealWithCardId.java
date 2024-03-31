package com.czy.zhongchou.util;

import com.czy.zhongchou.dao.AccountDAO;
import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.service.AccountService;

import java.sql.SQLException;
import java.util.Random;
//工具类
public class DealWithCardId {
    //获取随机数返回
    public String createCardId() throws SQLException, ClassNotFoundException {
        while (true) {
            String cardId = "";
            Random r = new Random(); //获取随机数
            for (int i = 0; i < 8; i++) {
                int n = r.nextInt(10);
                cardId += n;//将随机数连接起来
            }
            AccountService accountService=new AccountService();
            AccountDO accountDO = accountService.getAccountCardId(cardId);
            if (accountDO == null) {
                return cardId;
            }
        }
    }

}
