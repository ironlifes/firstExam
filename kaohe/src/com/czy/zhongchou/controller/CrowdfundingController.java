package com.czy.zhongchou.controller;

import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.entity.ManageDO;
import com.czy.zhongchou.service.CommentService;
import com.czy.zhongchou.service.CrowdfundingService;
import com.czy.zhongchou.service.ManageService;
import com.czy.zhongchou.service.RecordService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CrowdfundingController {
    private Scanner sc=new Scanner(System.in);
    //用来记住众筹对象
    private CrowdfundingDO crowdfundingDO1=new CrowdfundingDO();
    CrowdfundingService crowdfundingService=new CrowdfundingService();

    public void launchCrowdfunding() throws Exception {
        ManageDO manageDO=new ManageDO();

        manageDO.setCardId(AccountDO.accountDO.getCardId());

        System.out.println("请输入众筹发起者的姓名：");
        manageDO.setName(sc.next());

        System.out.println("请输入众筹发起者的年龄：");
        manageDO.setAge(sc.nextInt());

        System.out.println("请输入众筹发起者的性别：");
        manageDO.setSex(sc.next());

        System.out.println("请输入众筹发起的原因：");
        manageDO.setReason(sc.next());

        System.out.println("请输入众筹的金额：");
        manageDO.setMoney(sc.nextDouble());

        ManageService manageService=new ManageService();
        manageService.addCrowdfunding(manageDO);
        System.out.println("众筹发起成功，待管理员审核通过后，您有六小时的时间进行众筹~");
    }
    //查询众筹信息
    public void searchCrowdfunding() throws Exception {
        //删除超过限制时间的众筹
        timeDelete();
        //删除已筹齐的众筹
        moneyDelete();
        ArrayList<CrowdfundingDO> crowdfundingDOS=crowdfundingService.showCrowdfunding();
        //确保有众筹信息再展示
        if (crowdfundingDOS.isEmpty()) {
            System.out.println("目前还没有任何众筹信息~");
            return;
        }
        while (true) {
            System.out.println("各众筹者部分信息如下：");
            for (int i = 0; i < crowdfundingDOS.size(); i++) {
                CrowdfundingDO crowdfundingDO = crowdfundingDOS.get(i);
                System.out.println("序号："+crowdfundingDO.getNumber());
                System.out.println("姓名：" + crowdfundingDO.getName());
                System.out.println("年龄：" + crowdfundingDO.getAge());
                System.out.println("性别：" + crowdfundingDO.getSex());
                System.out.println();
            }
            while (true) {
                System.out.println("1、选择众筹者，以查看详细信息");
                System.out.println("2、返回上一页");
                String rs = sc.next();
                switch (rs){
                    case "1":
                        showDetails(crowdfundingDOS);
                        return;
                    case "2":
                        return;
                    default:
                        System.out.println("输入有误，请确认~");
                        break;
                }
            }
        }
    }

    //展示选中的众筹对象信息
    private void showDetails(ArrayList<CrowdfundingDO> crowdfundingDOS) throws Exception {
        System.out.println("请输入众筹者的序号：");
        int number=sc.nextInt();
        for (int i = 0; i < crowdfundingDOS.size(); i++) {
            CrowdfundingDO crowdfundingDO = crowdfundingDOS.get(i);
            if (number==crowdfundingDO.getNumber()) {
                System.out.println("姓名：" + crowdfundingDO.getName());
                System.out.println("年龄：" + crowdfundingDO.getAge());
                System.out.println("性别：" + crowdfundingDO.getSex());
                System.out.println("账号：" + crowdfundingDO.getCardId());
                System.out.println("原因：" + crowdfundingDO.getReason());
                System.out.println("众筹金额：" + crowdfundingDO.getMoney());
                System.out.println("众筹发起时间："+crowdfundingDO.getTime());
                crowdfundingDO1 = crowdfundingDO;
                break;
            }
        }
        choice(crowdfundingDOS);
        return;
    }

    //用户选择对众筹者进行的操作
    private void choice(ArrayList<CrowdfundingDO> crowdfundingDOS) throws Exception {
        while (true) {
            System.out.println("请选择：");
            System.out.println("1、查看评论");
            System.out.println("2、参与评论");
            System.out.println("3、进行捐款");
            System.out.println("4、返回上一页");
            int command= sc.nextInt();
            switch (command) {
                case 1:
                    CommentController commentController=new CommentController();
                    commentController.showComment(crowdfundingDO1);
                    break;
                case 2:
                    CommentController commentController1=new CommentController();
                    commentController1.addComment(crowdfundingDO1);
                    break;
                case 3:
                    donate(crowdfundingDOS, crowdfundingDO1);
                    return;
                case 4:
                    return;
                default:
                    System.out.println("输入有误，请确认~");
                    break;
            }
        }
    }

    //用户捐款
    private void donate(ArrayList<CrowdfundingDO> crowdfundingDOS,CrowdfundingDO crowdfundingDO) throws Exception {
        while (true) {
            System.out.println("请输入捐款金额：");
            double money=sc.nextDouble();
            //确保用户的余额足够
            if(AccountDO.accountDO.getMoney()>=money){
                //更新捐款后的数据
                crowdfundingService.updateDonate(money,crowdfundingDO);
                for (int i = 0; i <crowdfundingDOS.size(); i++) {
                    CrowdfundingDO cro3=crowdfundingDOS.get(i);
                    if(cro3.getCardId().equals(crowdfundingDO.getCardId())){
                        cro3.setMoney(cro3.getMoney()-money);
                        break;
                    }
                }

                RecordService recordService=new RecordService();
                recordService.addRecord(crowdfundingDO,money);
                System.out.println("捐款成功~");
                return;
            }else{
                System.out.println("您的余额不足，请确认~");
            }
        }

    }

    //修改个人信息
    public void update() throws Exception {
        System.out.println("请输入您要修改的信息：");
        System.out.println("1、姓名");
        System.out.println("2、邮箱");
        System.out.println("3、个人介绍");
        int command=sc.nextInt();
        switch(command){
            case 1:
                updateUserName();
                break;
            case 2:
                updateEmail();
                break;
            case 3:
                updateIntroduction();
                break;
            default:
                System.out.println("输入有误，请重新输入~");
                break;
        }
    }

    //修改姓名
    private void updateUserName() throws Exception {
        System.out.println("请输入您的新姓名：");
        String name=sc.next();
        AccountDO.accountDO.setUserName(name);
        System.out.println("修改成功~");
        crowdfundingService.updateUserName();
    }

    //修改邮箱
    private void updateEmail() throws Exception {
        System.out.println("请输入您的新邮箱：");
        String email=sc.next();
        AccountDO.accountDO.setEmail(email);
        System.out.println("修改成功~");
        crowdfundingService.updateEmail();
    }

    //修改个人介绍
    private void updateIntroduction() throws Exception {
        System.out.println("请输入您的新个人介绍：");
        String introduction=sc.next();
        AccountDO.accountDO.setIntroduction(introduction);
        System.out.println("修改成功~");
        crowdfundingService.updateIntroduction();
    }
    //删除已筹齐的众筹
    private void moneyDelete() throws Exception {
        //获取已筹齐的众筹编号
        ArrayList<CrowdfundingDO> crowdfundingDOS1=crowdfundingService.searchZero();
        //删除子表评论表中评论
        for (int i = 0; i < crowdfundingDOS1.size(); i++) {
            CrowdfundingDO crowdfundingDO=crowdfundingDOS1.get(i);
            CommentService commentService=new CommentService();
            commentService.deleteComment(crowdfundingDO.getNumber());
        }
        //在父表众筹表中删除
        crowdfundingService.deleteCrowdfundingByMoney();
    }

    //删除超过限制时间的众筹
    private void timeDelete() throws Exception {
        //获取超过限制时间的众筹编号
        ArrayList<CrowdfundingDO> crowdfundingDOS=crowdfundingService.searchTime();
        for (int i = 0; i < crowdfundingDOS.size(); i++) {
            CrowdfundingDO crowdfundingDO=crowdfundingDOS.get(i);
            CommentService commentService=new CommentService();
            commentService.deleteComment(crowdfundingDO.getNumber());
            crowdfundingService.deleteCrowdfundingByNumber(crowdfundingDO.getNumber());
        }
    }
}

