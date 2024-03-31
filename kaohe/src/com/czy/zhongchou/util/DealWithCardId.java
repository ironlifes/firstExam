package com.czy.zhongchou.util;

import com.czy.zhongchou.dao.AccountDAO;
import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.service.AccountService;

import java.sql.SQLException;
import java.util.Random;
//������
public class DealWithCardId {
    //��ȡ���������
    public String createCardId() throws SQLException, ClassNotFoundException {
        while (true) {
            String cardId = "";
            Random r = new Random(); //��ȡ�����
            for (int i = 0; i < 8; i++) {
                int n = r.nextInt(10);
                cardId += n;//���������������
            }
            AccountService accountService=new AccountService();
            AccountDO accountDO = accountService.getAccountCardId(cardId);
            if (accountDO == null) {
                return cardId;
            }
        }
    }

}
