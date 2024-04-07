package com.czy.zhongchou.controller;

import com.czy.zhongchou.Vue.Show;
import com.czy.zhongchou.entity.AccountDO;
import com.czy.zhongchou.service.AccountService;
import com.czy.zhongchou.util.CardIdUtil;

import java.util.Scanner;

public class AccountController {
    AccountService accountService= new AccountService();
    private Scanner sc=new Scanner(System.in);
    //ϵͳ����
    public void registerAccount() throws Exception {
        System.out.println("==ע�����==");
        AccountDO accountDO=new AccountDO();

        System.out.println("����������������");
        accountDO.setUserName(sc.next());

        System.out.println("���������󶨵����䣺");
        accountDO.setEmail(sc.next());

        System.out.println("���������ĸ��˽��ܣ�");
        accountDO.setIntroduction(sc.next());

        while (true) {
            System.out.println("�����������˻����룺");
            String passWord=sc.next();
            System.out.println("��ȷ�������˻����룺");
            String okPassWord=sc.next();
            if(passWord.equals(okPassWord)){
                accountDO.setPassWord(passWord);
                accountDO.setCardId(CardIdUtil.createCardId());
                accountDO.setMoney(1000);
                System.out.println("��ϲ��ע��ɹ��������˺��ǣ�"+accountDO.getCardId());
                System.out.println("���ĳ�ʼ�����1000.0Ԫ~");

                //����û�
                accountService.addAccountDO(accountDO);
                break;
            }else{
                System.out.println("������������벻ͬ������������~");
            }
        }
    }


    //��¼ҵ��
    public void login() throws Exception {
        System.out.println("==�û���¼==");
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
