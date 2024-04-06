package com.czy.zhongchou.service;

import com.czy.zhongchou.dao.CrowdfundingDAO;
import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.entity.ManageDO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CrowdfundingService {
    //创建dao包对象，以方便调用
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

    public void deleteCrowdfunding() throws Exception {
        crowdfundingDAO.deleteCrowdfunding();
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

    public void deleteCrowdfundindByAdministrator(int number) throws Exception {
        crowdfundingDAO.deleteCrowdfundingByAdministrator(number);
    }

    public ArrayList<CrowdfundingDO> searchZero() throws Exception {
        ArrayList<CrowdfundingDO> crowdfundingDOS=crowdfundingDAO.searchZero();
        return crowdfundingDOS;
    }
}
