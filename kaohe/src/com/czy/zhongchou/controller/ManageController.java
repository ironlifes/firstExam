package com.czy.zhongchou.controller;

import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.entity.ManageDO;
import com.czy.zhongchou.service.AccountService;
import com.czy.zhongchou.service.CommentService;
import com.czy.zhongchou.service.CrowdfundingService;
import com.czy.zhongchou.service.ManageService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ManageController {
    Scanner sc=new Scanner(System.in);
    //����Ա����
    public void administrator() throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println("���ɽ������²�����");
            System.out.println("1���鿴δ��˵��ڳ�");
            System.out.println("2���鿴�����ڳ���Ϣ");
            System.out.println("3���˳�����Ա���");
            int command=sc.nextInt();
            switch (command){
                case 1:
                    showUndoCrowdfunding();
                    break;
                case 2:
                    showAllCrowdfunding();
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }

    //չʾδ���ͨ�����ڳ���Ϣ
    private void showUndoCrowdfunding() throws SQLException, ClassNotFoundException {
        ManageService manageService=new ManageService();
        ArrayList<ManageDO> manageDOS= manageService.showUndoCrowdfunding();
        //ȷ�����ݿ����ж���
        if (manageDOS.isEmpty()) {
            System.out.println("Ŀǰ��û��δ��˵��ڳ���Ϣ~");
            return;
        }
        System.out.println("���ڳ���Ϣ���£�");
        for (int i = 0; i < manageDOS.size(); i++) {
            ManageDO manageDO = manageDOS.get(i);
            System.out.println("��ţ�" +manageDO.getNumber());
            System.out.println("������" + manageDO.getName());
            System.out.println("���䣺" + manageDO.getAge());
            System.out.println("�Ա�" + manageDO.getSex());
            System.out.println("�˺ţ�" + manageDO.getCardId());
            System.out.println("ԭ��" + manageDO.getReason());
            System.out.println("�ڳ��" + manageDO.getMoney());
            System.out.println();
        }
        firstChoice(manageDOS);
    }

    //��˽���
    private void firstChoice(ArrayList<ManageDO> manageDOS) throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println("��ѡ��");
            System.out.println("1��ѡ��ͨ�����ڳ�");
            System.out.println("2��������һҳ");
            int command = sc.nextInt();
            switch (command) {
                case 1:
                    System.out.println("���������ͨ���ߵ���ţ�");
                    int number=sc.nextInt();
                    for (int i = 0; i < manageDOS.size(); i++) {
                        ManageDO manageDO= manageDOS.get(i);
                        if(number==manageDO.getNumber()){
                            //�����ͨ�����ڳ���Ϣ���������ͨ�������ݿ���
                            CrowdfundingService crowdfundingService=new CrowdfundingService();
                            crowdfundingService.addCrowdfunding(manageDO);
                            //�����ͨ�����ڳ���Ϣ��δ���ͨ�������ݿ���ɾ��
                            ManageService manageService=new ManageService();
                            manageService.deleteCrowdfunding(manageDO);
                            System.out.println("���ڳ����ͨ��~");
                            break;
                        }
                    }
                    return;
                case 2:
                    return;
                default:
                    break;
            }
        }
    }

    private void showAllCrowdfunding() throws SQLException, ClassNotFoundException {
        CrowdfundingService crowdfundingService=new CrowdfundingService();
        //ɾ���ѳ�����ڳ���Ϣ
        //��ȡ�ѳ�����ڳ���
        ArrayList<CrowdfundingDO> crowdfundingDOS1=crowdfundingService.searchZero();
        //ɾ���ӱ����۱�������
        for (int i = 0; i < crowdfundingDOS1.size(); i++) {
            CrowdfundingDO crowdfundingDO=crowdfundingDOS1.get(i);
            CommentService commentService=new CommentService();
            commentService.deleteComment(crowdfundingDO.getNumber());
        }
        //�ڸ����ڳ����ɾ��
        crowdfundingService.deleteCrowdfunding();
        ArrayList<CrowdfundingDO> crowdfundingDOS=crowdfundingService.showCrowdfunding();
        if (crowdfundingDOS.isEmpty()) {
            System.out.println("Ŀǰ��û���κ��ڳ���Ϣ~");
            return;
        }
        for (int i = 0; i < crowdfundingDOS.size(); i++) {
            CrowdfundingDO crowdfundingDO=crowdfundingDOS.get(i);
            System.out.println("��ţ�"+crowdfundingDO.getNumber());
            System.out.println("������" + crowdfundingDO.getName());
            System.out.println("���䣺" + crowdfundingDO.getAge());
            System.out.println("�Ա�" + crowdfundingDO.getSex());
            System.out.println("�˺ţ�" + crowdfundingDO.getCardId());
            System.out.println("ԭ��" + crowdfundingDO.getReason());
            System.out.println("�ڳ��" + crowdfundingDO.getMoney());
            System.out.println();
        }
        secondChoice();
    }

    private void secondChoice() throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println("��ѡ��");
            System.out.println("1������ĳ���ڳ�");
            System.out.println("2��������һҳ");
            int command=sc.nextInt();
            switch(command){
                case 1:
                    System.out.println("�����볷���ڳ��ߵ���ţ�");
                    int number=sc.nextInt();
                    //ɾ���ӱ�����
                    CommentService commentService=new CommentService();
                    commentService.deleteComment(number);
                    //ɾ��������Ϣ
                    CrowdfundingService crowdfundingService=new CrowdfundingService();
                    crowdfundingService.deleteCrowdfundindByAdministrator(number);
                    System.out.println("�����ɹ�~");
                    return;
                case 2:
                    return;
                default:
                    break;
            }
        }
    }
}
