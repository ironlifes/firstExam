package com.czy.zhongchou.service;

import com.czy.zhongchou.dao.AccountDAO;
import com.czy.zhongchou.entity.AccountDO;

public class AccountService {
    //创建dao包，方便后续使用
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
