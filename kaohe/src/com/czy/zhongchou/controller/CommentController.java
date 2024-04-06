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
    CommentService commentService=new CommentService();

    //添加评论
    public void addComment(CrowdfundingDO crowdfundingDO) throws Exception {
        System.out.println("请输入您的评论：");
        String comment=sc.next();
        commentService.addComment(crowdfundingDO,comment);
        System.out.println("评论已添加~");
        return;
    }

    //展示评论
    public void showComment(CrowdfundingDO crowdfundingDO) throws Exception {
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
