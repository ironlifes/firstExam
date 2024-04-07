package com.czy.zhongchou.controller;

import com.czy.zhongchou.entity.RecordDO;
import com.czy.zhongchou.service.RecordService;

import java.util.ArrayList;
import java.util.Scanner;

public class RecordController {
    Scanner sc=new Scanner(System.in);
    public void showRecord() throws Exception {
        while (true) {
            System.out.println("1���鿴���н��׼�¼");
            System.out.println("2�������鿴ĳ�û��Ľ��׼�¼");
            System.out.println("3��������һҳ");
            int command=sc.nextInt();
            switch (command){
                case 1:
                    showAllRecord();
                    break;
                case 2:
                    showOneRecord();
                    break;
                case 3:
                    return;
                default:
                    break;
            }
        }
    }

    private void showAllRecord() throws Exception {
        RecordService recordService=new RecordService();
        ArrayList<RecordDO> recordDOS= recordService.showAllRecord();
        if(recordDOS.isEmpty()){
            System.out.println("Ŀǰ��û���κν��׼�¼~");
            return;
        }
        for (int i = 0; i < recordDOS.size(); i++) {
            RecordDO recordDO=recordDOS.get(i);
            System.out.print("����ߣ�"+recordDO.getDonor());
            System.out.print("  �տ��ߣ�"+recordDO.getRecipient());
            System.out.print("  ����"+recordDO.getMoney());
            System.out.println("  ���ʱ�䣺"+recordDO.getTimestamp());
        }
        System.out.println();
    }

    private void showOneRecord() throws Exception {
        System.out.println("���������ѯ���׼�¼�ľ���ߣ�");
        String name=sc.next();
        RecordService recordService=new RecordService();
        ArrayList<RecordDO> recordDOS=recordService.showOneRecord(name);
        if(recordDOS.isEmpty()){
            System.out.println("���û���δ�����κν���~");
            return;
        }
        for (int i = 0; i < recordDOS.size(); i++) {
            RecordDO recordDO=recordDOS.get(i);
            System.out.print("����ߣ�"+recordDO.getDonor());
            System.out.print("  �տ��ߣ�"+recordDO.getRecipient());
            System.out.print("  ����"+recordDO.getMoney());
            System.out.println("  ���ʱ�䣺"+recordDO.getTimestamp());
        }
        System.out.println();
    }
}
