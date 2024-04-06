package com.czy.zhongchou.service;

import com.czy.zhongchou.dao.AccountDAO;
import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.util.DBUtill;


import java.sql.SQLException;
import java.util.Random;

public class AccountService {
    //����dao�����������ʹ��
    private AccountDAO accountDAO= new AccountDAO();
    public void addAccountDO(AccountDO accountDO) throws Exception {
        accountDAO.addAccount(accountDO);
    }

    public Boolean searchAccount() throws Exception {
        if(accountDAO.searchAccount()){
            return true;
        }else {
            return false;
        }
    }

    public AccountDO getData(String cardId) throws Exception {
        AccountDO accountDO=accountDAO.getData(cardId);
        return accountDO;
    }
}
