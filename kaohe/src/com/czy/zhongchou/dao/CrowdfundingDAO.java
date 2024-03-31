package com.czy.zhongchou.dao;

import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.util.DBUtill;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class CrowdfundingDAO {
    public void addCrowdfunding(CrowdfundingDO crowdfundingDO) throws SQLException, ClassNotFoundException {
        Connection conn= DBUtill.getConn();
        String sql="insert into t_crowdfunding(card_id,name,age,sex,reason,money) values(?,?,?,?,?,?);";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,crowdfundingDO.getCardId());
        pstmt.setString(2, crowdfundingDO.getName());
        pstmt.setInt(3,crowdfundingDO.getAge());
        pstmt.setString(4,crowdfundingDO.getSex());
        pstmt.setString(5, crowdfundingDO.getReason());
        pstmt.setDouble(6,crowdfundingDO.getMoney());
        pstmt.executeUpdate();
        DBUtill.close(pstmt,conn);
    }

    public ArrayList<CrowdfundingDO> showCrowdfunding() throws ClassNotFoundException, SQLException {
        ArrayList<CrowdfundingDO> crowdfundingDOS=new ArrayList<>();
        Connection conn=DBUtill.getConn();
        String sql="select * from t_crowdfunding;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        ResultSet rs =pstmt.executeQuery();
        while(rs.next()){
            String cardId= rs.getNString("card_id");
            String name=rs.getString("name");
            int age=rs.getInt("age");
            String sex=rs.getString("sex");
            String reason=rs.getString("reason");
            double money=rs.getDouble("money");

            CrowdfundingDO crowdfundingDO=new CrowdfundingDO();
            crowdfundingDO.setCardId(cardId);
            crowdfundingDO.setName(name);
            crowdfundingDO.setAge(age);
            crowdfundingDO.setSex(sex);
            crowdfundingDO.setReason(reason);
            crowdfundingDO.setMoney(money);
            crowdfundingDOS.add(crowdfundingDO);
        }
        rs.close();
        DBUtill.close(pstmt,conn);
        return crowdfundingDOS;
    }

    public void updateDonate(AccountDO accountDO, double money, CrowdfundingDO crowdfundingDO) throws ClassNotFoundException, SQLException {
        Connection conn=DBUtill.getConn();
        String sql1="update t_crowdfunding set money=money-? where card_id=?;";
        PreparedStatement pstmt1=conn.prepareStatement(sql1);
        pstmt1.setDouble(1,money);
        pstmt1.setString(2,crowdfundingDO.getCardId());
        pstmt1.executeUpdate();
        pstmt1.close();

        String sql2="update t_account set money=money-? where card_id=?;";
        PreparedStatement pstmt2=conn.prepareStatement(sql2);
        pstmt2.setDouble(1,money);
        pstmt2.setString(2,accountDO.getCardId());
        pstmt2.executeUpdate();
        pstmt2.close();

        String sql3="update t_account set money=money+? where card_id=?;";
        PreparedStatement pstmt3=conn.prepareStatement(sql3);
        pstmt3.setDouble(1,money);
        pstmt3.setString(2,crowdfundingDO.getCardId());
        pstmt3.executeUpdate();
        pstmt3.close();
        DBUtill.closeConn(conn);

        accountDO.setMoney(accountDO.getMoney()-money);
    }

    public void deleteCrowdfunding() throws SQLException, ClassNotFoundException {
        Connection conn=DBUtill.getConn();
        String sql="delete from t_crowdfunding where money=0";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.executeUpdate();
        DBUtill.close(pstmt,conn);
    }

    public void updateUsername(AccountDO accountDO) throws ClassNotFoundException, SQLException {
        Connection conn=DBUtill.getConn();
        String sql="update t_account set user_name=? where card_id=?;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,accountDO.getUserName());
        pstmt.setString(2,accountDO.getCardId());
        pstmt.executeUpdate();

        DBUtill.close(pstmt,conn);
    }

    public void updateEamil(AccountDO accountDO) throws ClassNotFoundException, SQLException {
        Connection conn=DBUtill.getConn();
        String sql="update t_account set email=? where card_id=?;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,accountDO.getEmail());
        pstmt.setString(2,accountDO.getCardId());
        pstmt.executeUpdate();

        DBUtill.close(pstmt,conn);
    }

    public void updateInrtoduction(AccountDO accountDO) throws ClassNotFoundException, SQLException {
        Connection conn=DBUtill.getConn();
        String sql="update t_account set introduction=? where card_id=?;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,accountDO.getIntroduction());
        pstmt.setString(2,accountDO.getCardId());
        pstmt.executeUpdate();

        DBUtill.close(pstmt,conn);
    }
}
