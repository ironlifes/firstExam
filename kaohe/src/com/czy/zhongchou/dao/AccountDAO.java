package com.czy.zhongchou.dao;

import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.util.DBUtill;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.List;

public class AccountDAO {

    //在数据库中添加用户
    public void addAccount(AccountDO accountDO) throws Exception {
        Connection conn=DBUtill.getConn();
        String sql="insert into t_account(card_id,user_name,pass_word,email,introduction,money) values(?,?,?,?,?,?);";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,accountDO.getCardId());
        pstmt.setString(2,accountDO.getUserName());
        pstmt.setString(3,accountDO.getPassWord());
        pstmt.setString(4,accountDO.getEmail());
        pstmt.setString(5,accountDO.getIntroduction());
        pstmt.setDouble(6,accountDO.getMoney());
        pstmt.executeUpdate();
        DBUtill.close(pstmt,conn);
    }

    //在数据库中查询用户
    public AccountDO getData(String cardId) throws Exception {
        Connection conn=DBUtill.getConn();
        String sql="select * from t_account where card_id=?;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1,cardId);
        ResultSet rs= pstmt.executeQuery();
        if(rs.next()){
            AccountDO accountDO=new AccountDO();
            accountDO.setUserName(rs.getString("user_name"));
            accountDO.setCardId(rs.getString("card_id"));
            accountDO.setPassWord(rs.getString("pass_word"));
            accountDO.setEmail(rs.getString("email"));
            accountDO.setIntroduction(rs.getString("introduction"));
            accountDO.setMoney(rs.getDouble("money"));
            DBUtill.close(rs,pstmt,conn);
            return accountDO;
        }else {
            DBUtill.close(rs,pstmt,conn);
            return null;
        }
    }

    //在数据库中查询系统中是否有用户
    public boolean searchAccount() throws Exception {
        Connection conn=DBUtill.getConn();
        String sql="select * from t_account;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        ResultSet rs= pstmt.executeQuery();
        if(rs.next()){
            DBUtill.close(rs,pstmt,conn);
            return true;
        }else{
            DBUtill.close(rs,pstmt,conn);
            return false;
        }
    }
}
