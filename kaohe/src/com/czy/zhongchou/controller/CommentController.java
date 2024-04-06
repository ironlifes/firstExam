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

    //�������
    public void addComment(CrowdfundingDO crowdfundingDO) throws Exception {
        System.out.println("�������������ۣ�");
        String comment=sc.next();
        commentService.addComment(crowdfundingDO,comment);
        System.out.println("���������~");
        return;
    }

    //չʾ����
    public void showComment(CrowdfundingDO crowdfundingDO) throws Exception {
        ArrayList<CommentDO> commentDOS=commentService.showComment(crowdfundingDO);
        if (commentDOS.isEmpty()) {
            System.out.println("Ŀǰ��û���κ�����~");
            return;
        }
        System.out.println("�������������£�");
        for (int i = 0; i < commentDOS.size(); i++) {
            CommentDO commentDO=commentDOS.get(i);
            System.out.println("�����ߣ�"+commentDO.getName());
            System.out.println("�����ߣ�"+commentDO.getComment());
        }
    }
}
