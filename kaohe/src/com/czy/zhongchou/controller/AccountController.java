package com.czy.zhongchou.controller;

import com.czy.zhongchou.Vue.Show;
import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.service.AccountService;
import com.czy.zhongchou.util.CardIdUtil;

import java.util.Scanner;

public class AccountController {
    AccountService accountService= new AccountService();
    private Scanner sc=new Scanner(System.in);
    //系统界面
    public void registerAccount() throws Exception {
        System.out.println("==注册界面==");
        AccountDO accountDO=new AccountDO();

        System.out.println("请输入您的姓名：");
        accountDO.setUserName(sc.next());

        System.out.println("请输入您绑定的邮箱：");
        accountDO.setEmail(sc.next());

        System.out.println("请输入您的个人介绍：");
        accountDO.setIntroduction(sc.next());

        while (true) {
            System.out.println("请输入您的账户密码：");
            String passWord=sc.next();
            System.out.println("请确定您的账户密码：");
            String okPassWord=sc.next();
            if(passWord.equals(okPassWord)){
                accountDO.setPassWord(passWord);
                accountDO.setCardId(CardIdUtil.createCardId());
                accountDO.setMoney(1000);
                System.out.println("恭喜您注册成功，您的账号是："+accountDO.getCardId());
                System.out.println("您的初始余额是1000.0元~");

                //添加用户
                accountService.addAccountDO(accountDO);
                break;
            }else{
                System.out.println("两次输入的密码不同，请重新输入~");
            }
        }
    }


    //登录业务
    public void login() throws Exception {
        System.out.println("==用户登录==");
        //确保系统中有账号
        if(accountService.searchAccount()==false){
            System.out.println("系统中不存在账号，请先注册~");
            return;
        }

        while (true) {
            System.out.println("请输入您的账号：");
            String cardId=sc.next();
            AccountDO accountDO=accountService.getData(cardId);
            if(accountDO==null){
                System.out.println("您输入的账号不存在，请重新输入~");
                break;
            }
            else{
                while (true) {
                    System.out.println("请输入您的密码：");
                    String passWord=sc.next();
                    if(passWord.equals(accountDO.getPassWord())){
                        System.out.println("登录成功~");
                        //返回Show类展示登录后界面
                        Show show=new Show();
                        show.showUserCommand(accountDO);
                        return;
                    }else{
                        System.out.println("密码错误，请重新输入~");
                    }
                }
            }
        }
    }

}
