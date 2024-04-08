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
    //������ס�ڳ����
    private CrowdfundingDO crowdfundingDO1=new CrowdfundingDO();
    CrowdfundingService crowdfundingService=new CrowdfundingService();

    public void launchCrowdfunding() throws Exception {
        ManageDO manageDO=new ManageDO();

        manageDO.setCardId(AccountDO.accountDO.getCardId());

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
        System.out.println("�ڳ﷢��ɹ���������Ա���ͨ����������Сʱ��ʱ������ڳ�~");
    }
    //��ѯ�ڳ���Ϣ
    public void searchCrowdfunding() throws Exception {
        //ɾ����������ʱ����ڳ�
        timeDelete();
        //ɾ���ѳ�����ڳ�
        moneyDelete();
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
                System.out.println("��ţ�"+crowdfundingDO.getNumber());
                System.out.println("������" + crowdfundingDO.getName());
                System.out.println("���䣺" + crowdfundingDO.getAge());
                System.out.println("�Ա�" + crowdfundingDO.getSex());
                System.out.println();
            }
            while (true) {
                System.out.println("1��ѡ���ڳ��ߣ��Բ鿴��ϸ��Ϣ");
                System.out.println("2��������һҳ");
                String rs = sc.next();
                switch (rs){
                    case "1":
                        showDetails(crowdfundingDOS);
                        return;
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
    private void showDetails(ArrayList<CrowdfundingDO> crowdfundingDOS) throws Exception {
        System.out.println("�������ڳ��ߵ���ţ�");
        int number=sc.nextInt();
        for (int i = 0; i < crowdfundingDOS.size(); i++) {
            CrowdfundingDO crowdfundingDO = crowdfundingDOS.get(i);
            if (number==crowdfundingDO.getNumber()) {
                System.out.println("������" + crowdfundingDO.getName());
                System.out.println("���䣺" + crowdfundingDO.getAge());
                System.out.println("�Ա�" + crowdfundingDO.getSex());
                System.out.println("�˺ţ�" + crowdfundingDO.getCardId());
                System.out.println("ԭ��" + crowdfundingDO.getReason());
                System.out.println("�ڳ��" + crowdfundingDO.getMoney());
                System.out.println("�ڳ﷢��ʱ�䣺"+crowdfundingDO.getTime());
                crowdfundingDO1 = crowdfundingDO;
                break;
            }
        }
        choice(crowdfundingDOS);
        return;
    }

    //�û�ѡ����ڳ��߽��еĲ���
    private void choice(ArrayList<CrowdfundingDO> crowdfundingDOS) throws Exception {
        while (true) {
            System.out.println("��ѡ��");
            System.out.println("1���鿴����");
            System.out.println("2����������");
            System.out.println("3�����о��");
            System.out.println("4��������һҳ");
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
                    System.out.println("����������ȷ��~");
                    break;
            }
        }
    }

    //�û����
    private void donate(ArrayList<CrowdfundingDO> crowdfundingDOS,CrowdfundingDO crowdfundingDO) throws Exception {
        while (true) {
            System.out.println("���������");
            double money=sc.nextDouble();
            //ȷ���û�������㹻
            if(AccountDO.accountDO.getMoney()>=money){
                //���¾��������
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
                System.out.println("���ɹ�~");
                return;
            }else{
                System.out.println("�������㣬��ȷ��~");
            }
        }

    }

    //�޸ĸ�����Ϣ
    public void update() throws Exception {
        System.out.println("��������Ҫ�޸ĵ���Ϣ��");
        System.out.println("1������");
        System.out.println("2������");
        System.out.println("3�����˽���");
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
                System.out.println("������������������~");
                break;
        }
    }

    //�޸�����
    private void updateUserName() throws Exception {
        System.out.println("������������������");
        String name=sc.next();
        AccountDO.accountDO.setUserName(name);
        System.out.println("�޸ĳɹ�~");
        crowdfundingService.updateUserName();
    }

    //�޸�����
    private void updateEmail() throws Exception {
        System.out.println("���������������䣺");
        String email=sc.next();
        AccountDO.accountDO.setEmail(email);
        System.out.println("�޸ĳɹ�~");
        crowdfundingService.updateEmail();
    }

    //�޸ĸ��˽���
    private void updateIntroduction() throws Exception {
        System.out.println("�����������¸��˽��ܣ�");
        String introduction=sc.next();
        AccountDO.accountDO.setIntroduction(introduction);
        System.out.println("�޸ĳɹ�~");
        crowdfundingService.updateIntroduction();
    }
    //ɾ���ѳ�����ڳ�
    private void moneyDelete() throws Exception {
        //��ȡ�ѳ�����ڳ���
        ArrayList<CrowdfundingDO> crowdfundingDOS1=crowdfundingService.searchZero();
        //ɾ���ӱ����۱�������
        for (int i = 0; i < crowdfundingDOS1.size(); i++) {
            CrowdfundingDO crowdfundingDO=crowdfundingDOS1.get(i);
            CommentService commentService=new CommentService();
            commentService.deleteComment(crowdfundingDO.getNumber());
        }
        //�ڸ����ڳ����ɾ��
        crowdfundingService.deleteCrowdfundingByMoney();
    }

    //ɾ����������ʱ����ڳ�
    private void timeDelete() throws Exception {
        //��ȡ��������ʱ����ڳ���
        ArrayList<CrowdfundingDO> crowdfundingDOS=crowdfundingService.searchTime();
        for (int i = 0; i < crowdfundingDOS.size(); i++) {
            CrowdfundingDO crowdfundingDO=crowdfundingDOS.get(i);
            CommentService commentService=new CommentService();
            commentService.deleteComment(crowdfundingDO.getNumber());
            crowdfundingService.deleteCrowdfundingByNumber(crowdfundingDO.getNumber());
        }
    }
}

