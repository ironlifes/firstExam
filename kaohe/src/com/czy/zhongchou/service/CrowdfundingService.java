package com.czy.zhongchou.service;

import com.czy.zhongchou.dao.CrowdfundingDAO;
import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.entity.CrowdfundingDO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CrowdfundingService {
    //创建dao包对象，以方便调用
    CrowdfundingDAO crowdfundingDAO=new CrowdfundingDAO();
    public void addCrowdfunding(CrowdfundingDO crowdfundingDO) throws SQLException, ClassNotFoundException {
        crowdfundingDAO.addCrowdfunding(crowdfundingDO);
    }

    public ArrayList<CrowdfundingDO> showCrowdfunding() throws SQLException, ClassNotFoundException {
        ArrayList<CrowdfundingDO> crowdfundingDOS=crowdfundingDAO.showCrowdfunding();
        return crowdfundingDOS;
    }

    public void updateDonate(AccountDO accountDO, double money, CrowdfundingDO crowdfundingDO) throws SQLException, ClassNotFoundException {
        crowdfundingDAO.updateDonate(accountDO,money,crowdfundingDO);
    }

    public void deleteCrowdfunding() throws SQLException, ClassNotFoundException {
        crowdfundingDAO.deleteCrowdfunding();
    }

    public void updateUserName(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        crowdfundingDAO.updateUsername(accountDO);
    }

    public void updateEmail(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        crowdfundingDAO.updateEamil(accountDO);
    }

    public void updateIntroduction(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        crowdfundingDAO.updateInrtoduction(accountDO);
    }
}
