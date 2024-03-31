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
            System.out.println("==��ӭ�����ڳ�ϵͳ==");
            System.out.println("1����¼");
            System.out.println("2��ע��");
            System.out.println("3���˳�ϵͳ");
            System.out.println("��ѡ��");
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
                    System.out.println("�˳��ɹ�~");
                    return;
                default:
                    System.out.println("������������������~");
                    break;
            }
        }
    }

    public void showUserCommand(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println("�����Խ������²�����");
            System.out.println("1����ѯ���");
            System.out.println("2�������ڳ�");
            System.out.println("3���鿴�����ڳ���Ϣ");
            System.out.println("4���޸ĸ�����Ϣ");
            System.out.println("5���˳���¼");
            System.out.println("��ѡ��");
            int command=sc.nextInt();
            switch (command){
                case 1:
                    System.out.println("�������Ϊ��"+accountDO.getMoney());
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
                    System.out.println("�˳��ɹ�~");
                    return;
                default:
                    System.out.println("������������������~");
                    break;
            }
        }
    }
}
