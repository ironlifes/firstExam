package com.czy.zhongchou.Vue;

import com.czy.zhongchou.controller.AccountController;
import com.czy.zhongchou.controller.CrowdfundingController;
import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.service.AccountService;
import com.czy.zhongchou.util.DealWithCardId;

import java.sql.SQLException;
import java.util.Scanner;

public class Show {
    private Scanner sc=new Scanner(System.in);
    private AccountDO accountDO= new AccountDO();

    public void start() throws SQLException, ClassNotFoundException {
        System.out.println(this.accountDO.getCardId());
        while (true) {
            System.out.println("==欢迎进入众筹系统==");
            System.out.println("1、登录");
            System.out.println("2、注册");
            System.out.println("3、退出系统");
            System.out.println("请选择：");
            int command=sc.nextInt();
            switch (command){
                case 1:
                    AccountController accountController = new AccountController();
                    accountController.login();
                    break;
                case 2:
                    AccountController accountController1=new AccountController();
                    accountController1.registerAccount();
                    break;
                case 3:
                    System.out.println("退出成功~");
                    return;
                default:
                    System.out.println("输入有误，请重新输入~");
                    break;
            }
        }
    }

    public void showUserCommand(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println("您可以进行以下操作：");
            System.out.println("1、查询余额");
            System.out.println("2、发起众筹");
            System.out.println("3、查看所有众筹信息");
            System.out.println("4、修改个人信息");
            System.out.println("5、退出登录");
            System.out.println("请选择：");
            int command=sc.nextInt();
            switch (command){
                case 1:
                    System.out.println("您的余额为："+accountDO.getMoney());
                    break;
                case 2:
                    CrowdfundingController crowdfundingController=new CrowdfundingController();
                    crowdfundingController.launchCrowdfunding(accountDO);
                    break;
                case 3:
                    CrowdfundingController crowdfundingController1=new CrowdfundingController();
                    crowdfundingController1.searchCrowdfunding(accountDO);
                    break;
                case 4:
                    CrowdfundingController crowdfundingController2=new CrowdfundingController();
                    crowdfundingController2.update(accountDO);
                    break;
                case 5:
                    System.out.println("退出成功~");
                    return;
                default:
                    System.out.println("输入有误，请重新输入~");
                    break;
            }
        }
    }
}
