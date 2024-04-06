package com.czy.zhongchou.dao;

import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.entity.ManageDO;
import com.czy.zhongchou.util.DBUtill;

import java.sql.*;
import java.util.ArrayList;

public class CrowdfundingDAO {
    //�����ݿ�������û�
    public void addCrowdfunding(ManageDO manageDO) throws Exception {
        Connection conn= DBUtill.getConn();
        String sql="insert into t_crowdfunding(card_id,name,age,sex,reason,money) values(?,?,?,?,?,?);";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,manageDO.getCardId());
        pstmt.setString(2, manageDO.getName());
        pstmt.setInt(3,manageDO.getAge());
        pstmt.setString(4,manageDO.getSex());
        pstmt.setString(5, manageDO.getReason());
        pstmt.setDouble(6,manageDO.getMoney());
        pstmt.executeUpdate();
        DBUtill.close(pstmt,conn);
    }

    //�����ݿ��в�ѯ�����û���Ϣ
    public ArrayList<CrowdfundingDO> showCrowdfunding() throws Exception {
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
            int number=rs.getInt("number");

            //��װ�����Ա����ֱ��ʹ��
            CrowdfundingDO crowdfundingDO=new CrowdfundingDO();
            crowdfundingDO.setCardId(cardId);
            crowdfundingDO.setName(name);
            crowdfundingDO.setAge(age);
            crowdfundingDO.setSex(sex);
            crowdfundingDO.setReason(reason);
            crowdfundingDO.setMoney(money);
            crowdfundingDO.setNumber(number);
            crowdfundingDOS.add(crowdfundingDO);
        }
        DBUtill.close(rs,pstmt,conn);
        return crowdfundingDOS;
    }

    //�����ݿ��и��¾�������
    public void updateDonate(double money, CrowdfundingDO crowdfundingDO) throws Exception {
        Connection conn=DBUtill.getConn();
        //�ر��Զ��ύ
        conn.setAutoCommit(false);
        String sql1="update t_crowdfunding set money=money-? where card_id=?;";
        PreparedStatement pstmt1=conn.prepareStatement(sql1);
        pstmt1.setDouble(1,money);
        pstmt1.setString(2,crowdfundingDO.getCardId());
        pstmt1.executeUpdate();

        String sql2="update t_account set money=money-? where card_id=?;";
        PreparedStatement pstmt2=conn.prepareStatement(sql2);
        pstmt2.setDouble(1,money);
        pstmt2.setString(2, AccountDO.accountDO.getCardId());
        pstmt2.executeUpdate();

        String sql3="update t_account set money=money+? where card_id=?;";
        PreparedStatement pstmt3=conn.prepareStatement(sql3);
        pstmt3.setDouble(1,money);
        pstmt3.setString(2,crowdfundingDO.getCardId());
        pstmt3.executeUpdate();
        //�ֶ��ύ
        conn.commit();

        pstmt1.close();
        pstmt2.close();
        pstmt3.close();
        conn.close();

        AccountDO.accountDO.setMoney(AccountDO.accountDO.getMoney()-money);
    }

    //�����ݿ���ɾ���ѳ�����ڳ�
    public void deleteCrowdfunding() throws Exception {
        Connection conn=DBUtill.getConn();
        String sql="delete from t_crowdfunding where money=0";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.executeUpdate();
        DBUtill.close(pstmt,conn);
    }

    //�����ݿ����޸�����
    public void updateUsername() throws Exception {
        Connection conn=DBUtill.getConn();
        String sql="update t_account set user_name=? where card_id=?;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,AccountDO.accountDO.getUserName());
        pstmt.setString(2,AccountDO.accountDO.getCardId());
        pstmt.executeUpdate();

        DBUtill.close(pstmt,conn);
    }

    //�����ݿ����޸�����
    public void updateEamil() throws Exception {
        Connection conn=DBUtill.getConn();
        String sql="update t_account set email=? where card_id=?;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,AccountDO.accountDO.getEmail());
        pstmt.setString(2,AccountDO.accountDO.getCardId());
        pstmt.executeUpdate();

        DBUtill.close(pstmt,conn);
    }

    //�����ݿ����޸ĸ��˽���
    public void updateInrtoduction() throws Exception {
        Connection conn=DBUtill.getConn();
        String sql="update t_account set introduction=? where card_id=?;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,AccountDO.accountDO.getIntroduction());
        pstmt.setString(2,AccountDO.accountDO.getCardId());
        pstmt.executeUpdate();

        DBUtill.close(pstmt,conn);
    }

    //�Թ���Ա��������ݿ���ɾ���ڳ���Ϣ
    public void deleteCrowdfundingByAdministrator(int number) throws Exception {
        Connection conn=DBUtill.getConn();
        String sql="delete from t_crowdfunding where number=?;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,number);
        pstmt.executeUpdate();

        DBUtill.close(pstmt,conn);
    }

    //�����ݿ��в�ѯ�ѳ�����ڳ�
    public ArrayList<CrowdfundingDO> searchZero() throws Exception {
        ArrayList<CrowdfundingDO> crowdfundingDOS=new ArrayList<>();
        Connection conn=DBUtill.getConn();
        String sql="select * from t_crowdfunding where money=0;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            int number=rs.getInt("number");

            CrowdfundingDO crowdfundingDO=new CrowdfundingDO();
            crowdfundingDO.setNumber(number);
            crowdfundingDOS.add(crowdfundingDO);
        }
        DBUtill.close(rs,pstmt,conn);
        return crowdfundingDOS;
    }
}
