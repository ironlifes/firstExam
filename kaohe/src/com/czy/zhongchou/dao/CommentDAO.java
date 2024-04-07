package com.czy.zhongchou.dao;

import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.entity.CommentDO;
import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.util.DruidUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CommentDAO {
    //�����ݿ����������
    public void addComment(CrowdfundingDO crowdfundingDO,String comment) throws Exception {
        Connection conn= DruidUtil.getConn();
        String sql="insert into t_comment(name,comment,crowdfunding_number) values (?,?,?);";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setString(1, AccountDO.accountDO.getUserName());
        pstmt.setString(2,comment);
        pstmt.setInt(3,crowdfundingDO.getNumber());
        pstmt.executeUpdate();

        DruidUtil.close(pstmt,conn);
    }

    //�����ݿ��в�ѯ����
    public ArrayList<CommentDO> showComment(CrowdfundingDO crowdfundingDO) throws Exception {
        ArrayList<CommentDO> commentDOS=new ArrayList<>();
        Connection conn= DruidUtil.getConn();
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
        DruidUtil.close(rs,pstmt,conn);
        return commentDOS;
    }

    //�����ݿ���ɾ������
    public void deleteComment(int number) throws Exception {
        Connection conn= DruidUtil.getConn();
        String sql="delete from t_comment where crowdfunding_number=?;";
        PreparedStatement pstmt=conn.prepareStatement(sql);
        pstmt.setInt(1,number);
        pstmt.executeUpdate();
        DruidUtil.close(pstmt,conn);
    }
}
