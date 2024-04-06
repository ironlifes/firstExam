package com.czy.zhongchou.service;

import com.czy.zhongchou.dao.AccountDAO;
import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.util.DBUtill;


import java.sql.SQLException;
import java.util.Random;

public class AccountService {
    //创建dao包，方便后续使用
    private AccountDAO accountDAO= new AccountDAO();
    public void addAccountDO(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        accountDAO.addAccount(accountDO);
    }

    public Boolean searchAccount() throws SQLException, ClassNotFoundException {
        if(accountDAO.searchAccount()){
            return true;
        }else {
            return false;
        }
    }

    public AccountDO getData(String cardId) throws SQLException, ClassNotFoundException {
        AccountDO accountDO=accountDAO.getData(cardId);
        return accountDO;
    }
}
