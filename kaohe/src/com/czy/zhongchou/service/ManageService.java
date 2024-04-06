package com.czy.zhongchou.service;

import com.czy.zhongchou.dao.ManageDAO;
import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.entity.ManageDO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManageService {
    ManageDAO manageDAO=new ManageDAO();
    public void addCrowdfunding(ManageDO manageDO) throws Exception {
        manageDAO.addCrowdfunding(manageDO);
    }

    public ArrayList<ManageDO> showUndoCrowdfunding() throws Exception {
        ArrayList<ManageDO> manageDOS=manageDAO.showUndoCrowdfunding();
        return manageDOS;
    }

    public void deleteCrowdfunding(ManageDO manageDO) throws Exception {
        manageDAO.deleteCrowdfunding(manageDO);
    }
}
