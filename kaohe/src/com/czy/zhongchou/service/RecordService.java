package com.czy.zhongchou.service;

import com.czy.zhongchou.dao.RecordDAO;
import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.entity.RecordDO;

import java.util.ArrayList;

public class RecordService {
    RecordDAO recordDAO=new RecordDAO();
    public void addRecord(CrowdfundingDO crowdfundingDO,double money) throws Exception {
        recordDAO.addRecord(crowdfundingDO,money);
    }

    public ArrayList<RecordDO> showAllRecord() throws Exception {
        ArrayList<RecordDO> recordDOS= recordDAO.showAllRecord();
        return recordDOS;
    }

    public ArrayList<RecordDO> showOneRecord(String name) throws Exception {
        ArrayList<RecordDO> recordDOS=recordDAO.showOneRecord(name);
        return recordDOS;
    }
}
