package com.czy.zhongchou.service;

import com.czy.zhongchou.dao.CrowdfundingDAO;
import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.entity.ManageDO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CrowdfundingService {
    //创建dao包对象，以方便调用
    CrowdfundingDAO crowdfundingDAO=new CrowdfundingDAO();
    public void addCrowdfunding(ManageDO manageDO) throws SQLException, ClassNotFoundException {
        crowdfundingDAO.addCrowdfunding(manageDO);
    }

    public ArrayList<CrowdfundingDO> showCrowdfunding() throws SQLException, ClassNotFoundException {
        ArrayList<CrowdfundingDO> crowdfundingDOS=crowdfundingDAO.showCrowdfunding();
        return crowdfundingDOS;
    }

    public void updateDonate(double money, CrowdfundingDO crowdfundingDO) throws SQLException, ClassNotFoundException {
        crowdfundingDAO.updateDonate(money,crowdfundingDO);
    }

    public void deleteCrowdfunding() throws SQLException, ClassNotFoundException {
        crowdfundingDAO.deleteCrowdfunding();
    }

    public void updateUserName() throws SQLException, ClassNotFoundException {
        crowdfundingDAO.updateUsername();
    }

    public void updateEmail() throws SQLException, ClassNotFoundException {
        crowdfundingDAO.updateEamil();
    }

    public void updateIntroduction() throws SQLException, ClassNotFoundException {
        crowdfundingDAO.updateInrtoduction();
    }

    public void deleteCrowdfundindByAdministrator(int number) throws SQLException, ClassNotFoundException {
        crowdfundingDAO.deleteCrowdfundingByAdministrator(number);
    }

    public ArrayList<CrowdfundingDO> searchZero() throws SQLException, ClassNotFoundException {
        ArrayList<CrowdfundingDO> crowdfundingDOS=crowdfundingDAO.searchZero();
        return crowdfundingDOS;
    }
}
