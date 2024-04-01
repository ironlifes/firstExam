package com.czy.zhongchou.controller;

import com.czy.zhongchou.Vue.Show;
import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.service.AccountService;
import com.czy.zhongchou.util.DealWithCardId;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountController {

    private Scanner sc=new Scanner(System.in);

    //ϵͳ����
    public void registerAccount() throws SQLException, ClassNotFoundException {
        System.out.println("==ע�����==");
        AccountDO accountDO=new AccountDO();

        System.out.println("����������������");
        accountDO.setUserName(sc.next());

        System.out.println("���������󶨵����䣺");
        accountDO.setEmail(sc.next());

        System.out.println("���������ĸ��˽��ܣ�");
        accountDO.setIntroduction(sc.next());

        DealWithCardId dealWithCardId=new DealWithCardId();

        while (true) {
            System.out.println("�����������˻����룺");
            String passWord=sc.next();
            System.out.println("��ȷ�������˻����룺");
            String okPassWord=sc.next();
            if(passWord.equals(okPassWord)){
                accountDO.setPassWord(passWord);
                accountDO.setCardId(dealWithCardId.createCardId());
                accountDO.setMoney(1000);
                System.out.println("��ϲ��ע��ɹ��������˺��ǣ�"+accountDO.getCardId());
                System.out.println("���ĳ�ʼ�����1000.0Ԫ~");

                //����û�
                AccountService accountService= new AccountService();
                accountService.addAccountDO(accountDO);
                break;
            }else{
                System.out.println("������������벻ͬ������������~");
            }
        }
    }


    //��¼ҵ��
    public void login() throws SQLException, ClassNotFoundException {
        System.out.println("==�û���¼==");
        //��֤ϵͳ���Ƿ����˺�
        AccountService accountService= new AccountService();
        //ȷ��ϵͳ�����˺�
        if(accountService.searchAccount()==false){
            System.out.println("ϵͳ�в������˺ţ�����ע��~");
            return;
        }

        while (true) {
            System.out.println("�����������˺ţ�");
            String cardId=sc.next();
            AccountDO accountDO=accountService.getData(cardId);
            if(accountDO==null){
                System.out.println("��������˺Ų����ڣ�����������~");
                break;
            }
            else{
                while (true) {
                    System.out.println("�������������룺");
                    String passWord=sc.next();
                    if(passWord.equals(accountDO.getPassWord())){
                        System.out.println("��¼�ɹ�~");
                        //����Show��չʾ��¼�����
                        Show show=new Show();
                        show.showUserCommand(accountDO);
                        return;
                    }else{
                        System.out.println("�����������������~");
                    }
                }
            }
        }
    }

}
