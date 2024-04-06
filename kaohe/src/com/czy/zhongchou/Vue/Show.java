package com.czy.zhongchou.Vue;

import com.czy.zhongchou.controller.AccountController;
import com.czy.zhongchou.controller.CrowdfundingController;
import com.czy.zhongchou.controller.ManageController;
import com.czy.zhongchou.entity.AccountDO;

import java.sql.SQLException;
import java.util.Scanner;


public class Show {
    private Scanner sc=new Scanner(System.in);
    //展示给用户的登录界面
    public void start() throws Exception {
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

    //展示给用户登录后的界面
    public void showUserCommand(AccountDO accountDO) throws Exception {
        AccountDO.accountDO=accountDO;
        while (true) {
            System.out.println("您可以进行以下操作：");
            System.out.println("1、查询余额");
            System.out.println("2、发起众筹");
            System.out.println("3、查看所有众筹信息");
            System.out.println("4、修改个人信息");
            System.out.println("5、以管理员身份管理系统");
            System.out.println("6、退出登录");
            System.out.println("请选择：");
            int command=sc.nextInt();
            switch (command){
                case 1:
                    System.out.println("您的余额为："+AccountDO.accountDO.getMoney());
                    break;
                case 2:
                    CrowdfundingController crowdfundingController=new CrowdfundingController();
                    crowdfundingController.launchCrowdfunding();
                    break;
                case 3:
                    CrowdfundingController crowdfundingController1=new CrowdfundingController();
                    crowdfundingController1.searchCrowdfunding();
                    break;
                case 4:
                    CrowdfundingController crowdfundingController2=new CrowdfundingController();
                    crowdfundingController2.update();
                    break;
                case 5:
                    System.out.println("请输入管理密码：");
                    String password=sc.next();
                    if(password.equals("123456")){
                        System.out.println("密码正确~");
                        ManageController manageController=new ManageController();
                        manageController.administrator();
                    }else {
                        System.out.println("密码错误，请确认~");
                    }
                    break;
                case 6:
                    System.out.println("退出成功~");
                    return;
                default:
                    System.out.println("输入有误，请重新输入~");
                    break;
            }
        }
    }
}
