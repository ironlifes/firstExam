package com.czy.zhongchou.dao;

import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.entity.RecordDO;
import com.czy.zhongchou.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class RecordDAO {
    public void addRecord(CrowdfundingDO crowdfundingDO,double money) throws Exception {
        Connection conn= DruidUtil.getConn();
        String sql="insert into t_record(donor,recipient,money,time) values(?,?,?,now());";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, AccountDO.accountDO.getUserName());
        pstmt.setString(2,crowdfundingDO.getName());
        pstmt.setDouble(3,money);
        pstmt.executeUpdate();
        DruidUtil.close(pstmt,conn);
    }

    public ArrayList<RecordDO> showAllRecord() throws Exception {
        ArrayList<RecordDO> recordDOS=new ArrayList<>();
        Connection conn=DruidUtil.getConn();
        String sql="select * from t_record;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            String donor=rs.getString("donor");
            String recipient=rs.getString("recipient");
            double money=rs.getDouble("money");
            Timestamp timestamp=rs.getTimestamp("time");

            RecordDO recordDO=new RecordDO();
            recordDO.setDonor(donor);
            recordDO.setRecipient(recipient);
            recordDO.setMoney(money);
            recordDO.setTimestamp(timestamp);
            recordDOS.add(recordDO);
        }
        DruidUtil.close(rs,pstmt,conn);
        return recordDOS;
    }

    public ArrayList<RecordDO> showOneRecord(String name) throws Exception {
        ArrayList<RecordDO> recordDOS=new ArrayList<>();
        Connection conn=DruidUtil.getConn();
        String sql="select * from t_record where donor=?;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,name);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            String donor=rs.getString("donor");
            String recipient=rs.getString("recipient");
            double money=rs.getDouble("money");
            Timestamp timestamp=rs.getTimestamp("time");

            RecordDO recordDO=new RecordDO();
            recordDO.setDonor(donor);
            recordDO.setRecipient(recipient);
            recordDO.setMoney(money);
            recordDO.setTimestamp(timestamp);
            recordDOS.add(recordDO);
        }
        DruidUtil.close(rs,pstmt,conn);
        return recordDOS;
    }
}
