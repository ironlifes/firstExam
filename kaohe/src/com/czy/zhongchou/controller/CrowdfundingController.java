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
    //������ס�ڳ����
    private CrowdfundingDO crowdfundingDO1=new CrowdfundingDO();

    public void launchCrowdfunding(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        ManageDO manageDO=new ManageDO();

        manageDO.setCardId(accountDO.getCardId());

        System.out.println("�������ڳ﷢���ߵ�������");
        manageDO.setName(sc.next());

        System.out.println("�������ڳ﷢���ߵ����䣺");
        manageDO.setAge(sc.nextInt());

        System.out.println("�������ڳ﷢���ߵ��Ա�");
        manageDO.setSex(sc.next());

        System.out.println("�������ڳ﷢���ԭ��");
        manageDO.setReason(sc.next());

        System.out.println("�������ڳ�Ľ�");
        manageDO.setMoney(sc.nextDouble());

        ManageService manageService=new ManageService();
        manageService.addCrowdfunding(manageDO);
//        CrowdfundingService crowdfundingService=new CrowdfundingService();
//        crowdfundingService.addCrowdfunding(crowdfundingDO);
    }
    //��ѯ�ڳ���Ϣ
    public void searchCrowdfunding(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        CrowdfundingService crowdfundingService=new CrowdfundingService();
        //ɾ���ѳ�����ڳ���Ϣ
        crowdfundingService.deleteCrowdfunding();
        ArrayList<CrowdfundingDO> crowdfundingDOS=crowdfundingService.showCrowdfunding();
        //ȷ�����ڳ���Ϣ��չʾ
        if (crowdfundingDOS.isEmpty()) {
            System.out.println("Ŀǰ��û���κ��ڳ���Ϣ~");
            return;
        }
        while (true) {
            System.out.println("���ڳ��߲�����Ϣ���£�");
            for (int i = 0; i < crowdfundingDOS.size(); i++) {
                CrowdfundingDO crowdfundingDO = crowdfundingDOS.get(i);
                System.out.println("===��" + (i + 1) + "λ===");
                System.out.println("������" + crowdfundingDO.getName());
                System.out.println("���䣺" + crowdfundingDO.getAge());
                System.out.println("�Ա�" + crowdfundingDO.getSex());
            }
            while (true) {
                System.out.println("1��ѡ���ڳ��ߣ��Բ鿴��ϸ��Ϣ");
                System.out.println("2��������һҳ");
                String rs = sc.next();
                switch (rs){
                    case "1":
                        showDetails(crowdfundingDOS,accountDO);
                        break;
                    case "2":
                        return;
                    default:
                        System.out.println("����������ȷ��~");
                        break;
                }
            }
        }
    }

    //չʾѡ�е��ڳ������Ϣ
    private void showDetails(ArrayList<CrowdfundingDO> crowdfundingDOS,AccountDO accountDO) throws SQLException, ClassNotFoundException {
        System.out.println("�������ڳ���������");
        String name=sc.next();
        for (int i = 0; i < crowdfundingDOS.size(); i++) {
            CrowdfundingDO crowdfundingDO = crowdfundingDOS.get(i);
            if (name.equals(crowdfundingDO.getName())) {
                System.out.println("������" + crowdfundingDO.getName());
                System.out.println("���䣺" + crowdfundingDO.getAge());
                System.out.println("�Ա�" + crowdfundingDO.getSex());
                System.out.println("�˺ţ�" + crowdfundingDO.getCardId());
                System.out.println("ԭ��" + crowdfundingDO.getReason());
                System.out.println("�ڳ��" + crowdfundingDO.getMoney());
                crowdfundingDO1 = crowdfundingDO;
                break;
            }
        }
        choice(crowdfundingDOS,accountDO);
        return;
    }

    //�û�ѡ����ڳ��߽��еĲ���
    private void choice(ArrayList<CrowdfundingDO> crowdfundingDOS,AccountDO accountDO) throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println("��ѡ��");
            System.out.println("1����������");
            System.out.println("2�����о��");
            System.out.println("3��������һҳ");
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
                    System.out.println("����������ȷ��~");
                    break;
            }
        }
    }

    //�û����
    private void donate(ArrayList<CrowdfundingDO> crowdfundingDOS,CrowdfundingDO crowdfundingDO,AccountDO accountDO) throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println("���������");
            double money=sc.nextDouble();
            //ȷ���û�������㹻
            if(accountDO.getMoney()>=money){
                //���¾��������
                CrowdfundingService crowdfundingService=new CrowdfundingService();
                crowdfundingService.updateDonate(accountDO,money,crowdfundingDO);
                for (int i = 0; i <crowdfundingDOS.size(); i++) {
                    CrowdfundingDO cro3=crowdfundingDOS.get(i);
                    if(cro3.getCardId().equals(crowdfundingDO.getCardId())){
                        cro3.setMoney(cro3.getMoney()-money);
                        break;
                    }
                }
                System.out.println("���ɹ�~");
                return;
            }else{
                System.out.println("�������㣬��ȷ��~");
            }
        }

    }

    //�޸ĸ�����Ϣ
    public void update(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        System.out.println("��������Ҫ�޸ĵ���Ϣ��");
        System.out.println("1������");
        System.out.println("2������");
        System.out.println("3�����˽���");
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
                System.out.println("������������������~");
                break;
        }
    }

    //�޸�����
    private void updateUserName(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        System.out.println("������������������");
        String name=sc.next();
        accountDO.setUserName(name);
        System.out.println("�޸ĳɹ�~");
        CrowdfundingService crowdfundingService=new CrowdfundingService();
        crowdfundingService.updateUserName(accountDO);
    }

    //�޸�����
    private void updateEmail(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        System.out.println("���������������䣺");
        String email=sc.next();
        accountDO.setEmail(email);
        System.out.println("�޸ĳɹ�~");
        CrowdfundingService crowdfundingService=new CrowdfundingService();
        crowdfundingService.updateEmail(accountDO);
    }

    //�޸ĸ��˽���
    private void updateIntroduction(AccountDO accountDO) throws SQLException, ClassNotFoundException {
        System.out.println("�����������¸��˽��ܣ�");
        String introduction=sc.next();
        accountDO.setIntroduction(introduction);
        System.out.println("�޸ĳɹ�~");
        CrowdfundingService crowdfundingService=new CrowdfundingService();
        crowdfundingService.updateIntroduction(accountDO);
    }
}

