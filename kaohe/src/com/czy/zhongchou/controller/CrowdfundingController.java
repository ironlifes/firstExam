package com.czy.zhongchou.controller;

import com.czy.zhongchou.dao.ManageDAO;
import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.entity.ManageDO;
import com.czy.zhongchou.service.CrowdfundingService;
import com.czy.zhongchou.service.ManageService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CrowdfundingController {
    private Scanner sc=new Scanner(System.in);
    //用来记住众筹对象
    private CrowdfundingDO crowdfundingDO1=new CrowdfundingDO();

    public void launchCrowdfunding(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        ManageDO manageDO=new ManageDO();

        manageDO.setCardId(accountDO.getCardId());

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
//        CrowdfundingService crowdfundingService=new CrowdfundingService();
//        crowdfundingService.addCrowdfunding(crowdfundingDO);
    }
    //查询众筹信息
    public void searchCrowdfunding(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        CrowdfundingService crowdfundingService=new CrowdfundingService();
        //删除已筹齐的众筹信息
        crowdfundingService.deleteCrowdfunding();
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
                System.out.println("===第" + (i + 1) + "位===");
                System.out.println("姓名：" + crowdfundingDO.getName());
                System.out.println("年龄：" + crowdfundingDO.getAge());
                System.out.println("性别：" + crowdfundingDO.getSex());
            }
            while (true) {
                System.out.println("1、选择众筹者，以查看详细信息");
                System.out.println("2、返回上一页");
                String rs = sc.next();
                switch (rs){
                    case "1":
                        showDetails(crowdfundingDOS,accountDO);
                        break;
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
    private void showDetails(ArrayList<CrowdfundingDO> crowdfundingDOS,AccountDO accountDO) throws SQLException, ClassNotFoundException {
        System.out.println("请输入众筹者姓名：");
        String name=sc.next();
        for (int i = 0; i < crowdfundingDOS.size(); i++) {
            CrowdfundingDO crowdfundingDO = crowdfundingDOS.get(i);
            if (name.equals(crowdfundingDO.getName())) {
                System.out.println("姓名：" + crowdfundingDO.getName());
                System.out.println("年龄：" + crowdfundingDO.getAge());
                System.out.println("性别：" + crowdfundingDO.getSex());
                System.out.println("账号：" + crowdfundingDO.getCardId());
                System.out.println("原因：" + crowdfundingDO.getReason());
                System.out.println("众筹金额：" + crowdfundingDO.getMoney());
                crowdfundingDO1 = crowdfundingDO;
                break;
            }
        }
        choice(crowdfundingDOS,accountDO);
        return;
    }

    //用户选择对众筹者进行的操作
    private void choice(ArrayList<CrowdfundingDO> crowdfundingDOS,AccountDO accountDO) throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println("请选择：");
            System.out.println("1、参与评论");
            System.out.println("2、进行捐款");
            System.out.println("3、返回上一页");
            String rs2 = sc.next();
            switch (rs2) {
                case "1":
                    break;
                case "2":
                    donate(crowdfundingDOS, crowdfundingDO1,accountDO);
                    return;
                case "3":
                    return;
                default:
                    System.out.println("输入有误，请确认~");
                    break;
            }
        }
    }

    //用户捐款
    private void donate(ArrayList<CrowdfundingDO> crowdfundingDOS,CrowdfundingDO crowdfundingDO,AccountDO accountDO) throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println("请输入捐款金额：");
            double money=sc.nextDouble();
            //确保用户的余额足够
            if(accountDO.getMoney()>=money){
                //更新捐款后的数据
                CrowdfundingService crowdfundingService=new CrowdfundingService();
                crowdfundingService.updateDonate(accountDO,money,crowdfundingDO);
                for (int i = 0; i <crowdfundingDOS.size(); i++) {
                    CrowdfundingDO cro3=crowdfundingDOS.get(i);
                    if(cro3.getCardId().equals(crowdfundingDO.getCardId())){
                        cro3.setMoney(cro3.getMoney()-money);
                        break;
                    }
                }
                System.out.println("捐款成功~");
                return;
            }else{
                System.out.println("您的余额不足，请确认~");
            }
        }

    }

    //修改个人信息
    public void update(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        System.out.println("请输入您要修改的信息：");
        System.out.println("1、姓名");
        System.out.println("2、邮箱");
        System.out.println("3、个人介绍");
        int command=sc.nextInt();
        switch(command){
            case 1:
                updateUserName(accountDO);
                break;
            case 2:
                updateEmail(accountDO);
                break;
            case 3:
                updateIntroduction(accountDO);
                break;
            default:
                System.out.println("输入有误，请重新输入~");
                break;
        }
    }

    //修改姓名
    private void updateUserName(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        System.out.println("请输入您的新姓名：");
        String name=sc.next();
        accountDO.setUserName(name);
        System.out.println("修改成功~");
        CrowdfundingService crowdfundingService=new CrowdfundingService();
        crowdfundingService.updateUserName(accountDO);
    }

    //修改邮箱
    private void updateEmail(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        System.out.println("请输入您的新邮箱：");
        String email=sc.next();
        accountDO.setEmail(email);
        System.out.println("修改成功~");
        CrowdfundingService crowdfundingService=new CrowdfundingService();
        crowdfundingService.updateEmail(accountDO);
    }

    //修改个人介绍
    private void updateIntroduction(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        System.out.println("请输入您的新个人介绍：");
        String introduction=sc.next();
        accountDO.setIntroduction(introduction);
        System.out.println("修改成功~");
        CrowdfundingService crowdfundingService=new CrowdfundingService();
        crowdfundingService.updateIntroduction(accountDO);
    }
}

