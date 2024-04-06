package com.czy.zhongchou.dao;

import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.entity.CommentDO;
import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.util.DBUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommentDAO {
    //在数据库中添加评论
    public void addComment(CrowdfundingDO crowdfundingDO,String comment) throws SQLException, ClassNotFoundException {
        Connection conn=DBUtill.getConn();
        String sql="insert into t_comment(name,comment,crowdfunding_number) values (?,?,?);";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, AccountDO.accountDO.getUserName());
        pstmt.setString(2,comment);
        pstmt.setInt(3,crowdfundingDO.getNumber());
        pstmt.executeUpdate();

        DBUtill.close(pstmt,conn);
    }

    //在数据库中查询评论
    public ArrayList<CommentDO> showComment(CrowdfundingDO crowdfundingDO) throws SQLException, ClassNotFoundException {
        ArrayList<CommentDO> commentDOS=new ArrayList<>();
        Connection conn=DBUtill.getConn();
        String sql="select * from t_comment where crowdfunding_number=?";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,crowdfundingDO.getNumber());
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            String name=rs.getString("name");
            String comment=rs.getString("comment");

            CommentDO commentDO=new CommentDO();
            commentDO.setName(name);
            commentDO.setComment(comment);
            commentDOS.add(commentDO);
        }
        rs.close();
        DBUtill.close(pstmt,conn);
        return commentDOS;
    }

    //在数据库中删除评论
    public void deleteComment(int number) throws SQLException, ClassNotFoundException {
        Connection conn=DBUtill.getConn();
        String sql="delete from t_comment where crowdfunding_number=?;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,number);
        pstmt.executeUpdate();
        DBUtill.close(pstmt,conn);
    }
}
