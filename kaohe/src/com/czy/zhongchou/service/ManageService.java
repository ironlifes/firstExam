package com.czy.zhongchou.service;

import com.czy.zhongchou.dao.ManageDAO;
import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.entity.ManageDO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageService {
    ManageDAO manageDAO=new ManageDAO();
    public void addCrowdfunding(ManageDO manageDO) throws SQLException, ClassNotFoundException {
        manageDAO.addCrowdfunding(manageDO);
    }

    public ArrayList<ManageDO> showUndoCrowdfunding() throws SQLException, ClassNotFoundException {
        ArrayList<ManageDO> manageDOS=manageDAO.showUndoCrowdfunding();
        return manageDOS;
    }

    public void deleteCrowdfunding(ManageDO manageDO) throws SQLException, ClassNotFoundException {
        manageDAO.deleteCrowdfunding(manageDO);
    }
}
