package com.czy.zhongchou.service;

import com.czy.zhongchou.dao.CommentDAO;
import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.entity.CommentDO;
import com.czy.zhongchou.entity.CrowdfundingDO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommentService {
    CommentDAO commentDAO=new CommentDAO();
    public void addComment(CrowdfundingDO crowdfundingDO,String comment) throws Exception {
        commentDAO.addComment(crowdfundingDO,comment);
    }

    public ArrayList<CommentDO> showComment(CrowdfundingDO crowdfundingDO) throws Exception {
        ArrayList<CommentDO> commentDOS=commentDAO.showComment(crowdfundingDO);
        return commentDOS;
    }

    public void deleteComment(int number) throws Exception {
        commentDAO.deleteComment(number);
    }
}
