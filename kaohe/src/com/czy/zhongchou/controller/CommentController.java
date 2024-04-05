package com.czy.zhongchou.controller;

import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.entity.CommentDO;
import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.service.CommentService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CommentController {
    Scanner sc=new Scanner(System.in);
    public void addComment(AccountDO accountDO, CrowdfundingDO crowdfundingDO) throws SQLException, ClassNotFoundException {
        System.out.println("请输入您的评论：");
        String comment=sc.next();
        CommentService commentService=new CommentService();
        commentService.addComment(accountDO,crowdfundingDO,comment);
        System.out.println("评论已添加~");
        return;
    }

    public void showComment(CrowdfundingDO crowdfundingDO) throws SQLException, ClassNotFoundException {
        CommentService commentService=new CommentService();
        ArrayList<CommentDO> commentDOS=commentService.showComment(crowdfundingDO);
        if (commentDOS.isEmpty()) {
            System.out.println("目前还没有任何评论~");
            return;
        }
        System.out.println("各评论内容如下：");
        for (int i = 0; i < commentDOS.size(); i++) {
            CommentDO commentDO=commentDOS.get(i);
            System.out.println("评论者："+commentDO.getName());
            System.out.println("评论者："+commentDO.getComment());
        }
    }
}
