package com.czy.zhongchou.service;

import com.czy.zhongchou.dao.CrowdfundingDAO;
import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.entity.ManageDO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CrowdfundingService {
    //����dao�������Է������
    CrowdfundingDAO crowdfundingDAO=new CrowdfundingDAO();
    public void addCrowdfunding(ManageDO manageDO) throws Exception {
        crowdfundingDAO.addCrowdfunding(manageDO);
    }

    public ArrayList<CrowdfundingDO> showCrowdfunding() throws Exception {
        ArrayList<CrowdfundingDO> crowdfundingDOS=crowdfundingDAO.showCrowdfunding();
        return crowdfundingDOS;
    }

    public void updateDonate(double money, CrowdfundingDO crowdfundingDO) throws Exception {
        crowdfundingDAO.updateDonate(money,crowdfundingDO);
    }

    public void deleteCrowdfundingByMoney() throws Exception {
        crowdfundingDAO.deleteCrowdfundingByMoney();
    }

    public void updateUserName() throws Exception {
        crowdfundingDAO.updateUsername();
    }

    public void updateEmail() throws Exception {
        crowdfundingDAO.updateEamil();
    }

    public void updateIntroduction() throws Exception {
        crowdfundingDAO.updateInrtoduction();
    }

    public void deleteCrowdfundingByAdministrator(int number) throws Exception {
        crowdfundingDAO.deleteCrowdfundingByAdministrator(number);
    }

    public ArrayList<CrowdfundingDO> searchZero() throws Exception {
        ArrayList<CrowdfundingDO> crowdfundingDOS=crowdfundingDAO.searchZero();
        return crowdfundingDOS;
    }

    public ArrayList<CrowdfundingDO> searchTime() throws Exception {
        ArrayList<CrowdfundingDO> crowdfundingDOS=crowdfundingDAO.searchTime();
        return crowdfundingDOS;
    }

    public void deleteCrowdfundingByNumber(int number) throws Exception {
        crowdfundingDAO.deleteCrowdfundingByNumber(number);
    }
}
