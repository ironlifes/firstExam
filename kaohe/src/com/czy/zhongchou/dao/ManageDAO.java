package com.czy.zhongchou.dao;

import com.czy.zhongchou.entity.ManageDO;
import com.czy.zhongchou.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

//操作用户发起但是还未经审核的众筹
public class ManageDAO {

    public void addCrowdfunding(ManageDO manageDO) throws Exception {
        Connection conn= DruidUtil.getConn();
        String sql="insert into t_managecrowdfunding(card_id,name,age,sex,reason,money) values(?,?,?,?,?,?);";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,manageDO.getCardId());
        pstmt.setString(2, manageDO.getName());
        pstmt.setInt(3,manageDO.getAge());
        pstmt.setString(4,manageDO.getSex());
        pstmt.setString(5, manageDO.getReason());
        pstmt.setDouble(6,manageDO.getMoney());
        pstmt.executeUpdate();
        DruidUtil.close(pstmt,conn);
    }

    public ArrayList<ManageDO> showUndoCrowdfunding() throws Exception {
        ArrayList<ManageDO> manageDOS=new ArrayList<>();
        Connection conn= DruidUtil.getConn();
        String sql="select * from t_managecrowdfunding;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        ResultSet rs =pstmt.executeQuery();
        while(rs.next()){
            String cardId= rs.getNString("card_id");
            String name=rs.getString("name");
            int age=rs.getInt("age");
            String sex=rs.getString("sex");
            String reason=rs.getString("reason");
            double money=rs.getDouble("money");
            int number=rs.getInt("number");

            //封装对象，以便后续直接使用
            ManageDO manageDO=new ManageDO();
            manageDO.setCardId(cardId);
            manageDO.setName(name);
            manageDO.setAge(age);
            manageDO.setSex(sex);
            manageDO.setReason(reason);
            manageDO.setMoney(money);
            manageDO.setNumber(number);
            manageDOS.add(manageDO);
        }
        DruidUtil.close(rs,pstmt,conn);
        return manageDOS;
    }

    public void deleteCrowdfunding(ManageDO manageDO) throws Exception {
        Connection conn= DruidUtil.getConn();
        String sql="delete from t_managecrowdfunding where number=?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,manageDO.getNumber());
        pstmt.executeUpdate();
        DruidUtil.close(pstmt,conn);
    }
}
