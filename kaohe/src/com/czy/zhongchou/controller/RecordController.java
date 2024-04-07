package com.czy.zhongchou.controller;

import com.czy.zhongchou.entity.RecordDO;
import com.czy.zhongchou.service.RecordService;

import java.util.ArrayList;
import java.util.Scanner;

public class RecordController {
    Scanner sc=new Scanner(System.in);
    public void showRecord() throws Exception {
        while (true) {
            System.out.println("1、查看所有交易记录");
            System.out.println("2、单独查看某用户的交易记录");
            System.out.println("3、返回上一页");
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
            System.out.println("目前还没有任何交易记录~");
            return;
        }
        for (int i = 0; i < recordDOS.size(); i++) {
            RecordDO recordDO=recordDOS.get(i);
            System.out.print("捐款者："+recordDO.getDonor());
            System.out.print("  收款者："+recordDO.getRecipient());
            System.out.print("  捐款金额："+recordDO.getMoney());
            System.out.println("  捐款时间："+recordDO.getTimestamp());
        }
        System.out.println();
    }

    private void showOneRecord() throws Exception {
        System.out.println("请输入想查询交易记录的捐款者：");
        String name=sc.next();
        RecordService recordService=new RecordService();
        ArrayList<RecordDO> recordDOS=recordService.showOneRecord(name);
        if(recordDOS.isEmpty()){
            System.out.println("该用户还未进行任何交易~");
            return;
        }
        for (int i = 0; i < recordDOS.size(); i++) {
            RecordDO recordDO=recordDOS.get(i);
            System.out.print("捐款者："+recordDO.getDonor());
            System.out.print("  收款者："+recordDO.getRecipient());
            System.out.print("  捐款金额："+recordDO.getMoney());
            System.out.println("  捐款时间："+recordDO.getTimestamp());
        }
        System.out.println();
    }
}
