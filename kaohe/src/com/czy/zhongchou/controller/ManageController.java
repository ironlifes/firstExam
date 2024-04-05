package com.czy.zhongchou.controller;

import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.entity.ManageDO;
import com.czy.zhongchou.service.AccountService;
import com.czy.zhongchou.service.CommentService;
import com.czy.zhongchou.service.CrowdfundingService;
import com.czy.zhongchou.service.ManageService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ManageController {
    Scanner sc=new Scanner(System.in);
    //管理员界面
    public void administrator() throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println("您可进行以下操作：");
            System.out.println("1、查看未审核的众筹");
            System.out.println("2、查看所有众筹信息");
            System.out.println("3、退出管理员身份");
            int command=sc.nextInt();
            switch (command){
                case 1:
                    showUndoCrowdfunding();
                    break;
                case 2:
                    showAllCrowdfunding();
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }

    //展示未审核通过的众筹信息
    private void showUndoCrowdfunding() throws SQLException, ClassNotFoundException {
        ManageService manageService=new ManageService();
        ArrayList<ManageDO> manageDOS= manageService.showUndoCrowdfunding();
        //确保数据库中有对象
        if (manageDOS.isEmpty()) {
            System.out.println("目前还没有未审核的众筹信息~");
            return;
        }
        System.out.println("各众筹信息如下：");
        for (int i = 0; i < manageDOS.size(); i++) {
            ManageDO manageDO = manageDOS.get(i);
            System.out.println("序号：" +manageDO.getNumber());
            System.out.println("姓名：" + manageDO.getName());
            System.out.println("年龄：" + manageDO.getAge());
            System.out.println("性别：" + manageDO.getSex());
            System.out.println("账号：" + manageDO.getCardId());
            System.out.println("原因：" + manageDO.getReason());
            System.out.println("众筹金额：" + manageDO.getMoney());
            System.out.println();
        }
        firstChoice(manageDOS);
    }

    //审核界面
    private void firstChoice(ArrayList<ManageDO> manageDOS) throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println("请选择：");
            System.out.println("1、选择通过的众筹");
            System.out.println("2、返回上一页");
            int command = sc.nextInt();
            switch (command) {
                case 1:
                    System.out.println("请输入审核通过者的序号：");
                    int number=sc.nextInt();
                    for (int i = 0; i < manageDOS.size(); i++) {
                        ManageDO manageDO= manageDOS.get(i);
                        if(number==manageDO.getNumber()){
                            //将审核通过的众筹信息加入已审核通过的数据库中
                            CrowdfundingService crowdfundingService=new CrowdfundingService();
                            crowdfundingService.addCrowdfunding(manageDO);
                            //将审核通过的众筹信息从未审核通过的数据库中删除
                            ManageService manageService=new ManageService();
                            manageService.deleteCrowdfunding(manageDO);
                            System.out.println("该众筹审核通过~");
                            break;
                        }
                    }
                    return;
                case 2:
                    return;
                default:
                    break;
            }
        }
    }

    private void showAllCrowdfunding() throws SQLException, ClassNotFoundException {
        CrowdfundingService crowdfundingService=new CrowdfundingService();
        //删除已筹齐的众筹信息
        //获取已筹齐的众筹编号
        ArrayList<CrowdfundingDO> crowdfundingDOS1=crowdfundingService.searchZero();
        //删除子表评论表中评论
        for (int i = 0; i < crowdfundingDOS1.size(); i++) {
            CrowdfundingDO crowdfundingDO=crowdfundingDOS1.get(i);
            CommentService commentService=new CommentService();
            commentService.deleteComment(crowdfundingDO.getNumber());
        }
        //在父表众筹表中删除
        crowdfundingService.deleteCrowdfunding();
        ArrayList<CrowdfundingDO> crowdfundingDOS=crowdfundingService.showCrowdfunding();
        if (crowdfundingDOS.isEmpty()) {
            System.out.println("目前还没有任何众筹信息~");
            return;
        }
        for (int i = 0; i < crowdfundingDOS.size(); i++) {
            CrowdfundingDO crowdfundingDO=crowdfundingDOS.get(i);
            System.out.println("序号："+crowdfundingDO.getNumber());
            System.out.println("姓名：" + crowdfundingDO.getName());
            System.out.println("年龄：" + crowdfundingDO.getAge());
            System.out.println("性别：" + crowdfundingDO.getSex());
            System.out.println("账号：" + crowdfundingDO.getCardId());
            System.out.println("原因：" + crowdfundingDO.getReason());
            System.out.println("众筹金额：" + crowdfundingDO.getMoney());
            System.out.println();
        }
        secondChoice();
    }

    private void secondChoice() throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println("请选择：");
            System.out.println("1、撤销某个众筹");
            System.out.println("2、返回上一页");
            int command=sc.nextInt();
            switch(command){
                case 1:
                    System.out.println("请输入撤销众筹者的序号：");
                    int number=sc.nextInt();
                    //删除子表评论
                    CommentService commentService=new CommentService();
                    commentService.deleteComment(number);
                    //删除父表信息
                    CrowdfundingService crowdfundingService=new CrowdfundingService();
                    crowdfundingService.deleteCrowdfundindByAdministrator(number);
                    System.out.println("撤销成功~");
                    return;
                case 2:
                    return;
                default:
                    break;
            }
        }
    }
}
