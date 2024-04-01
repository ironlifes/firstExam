package com.czy.zhongchou.controller;

import com.czy.zhongchou.entity.CrowdfundingDO;
import com.czy.zhongchou.entity.ManageDO;
import com.czy.zhongchou.service.AccountService;
import com.czy.zhongchou.service.CrowdfundingService;
import com.czy.zhongchou.service.ManageService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ManageController {
    Scanner sc=new Scanner(System.in);
    //管理员界面
    public void administrator() throws SQLException, ClassNotFoundException {
        System.out.println("1、查看未审核的众筹");
        System.out.println("2、查看所有众筹信息");
        int command=sc.nextInt();
        switch (command){
            case 1:
                showUndoCrowdfunding();
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    //展示未审核通过的众筹信息
    private void showUndoCrowdfunding() throws SQLException, ClassNotFoundException {
        ManageService manageService=new ManageService();
        ArrayList<ManageDO> manageDOS= manageService.showUndoCrowdfunding();
        //确保数据库中有对象
        if (manageDOS.isEmpty()) {
            System.out.println("目前还没有任何众筹信息~");
            return;
        }
        System.out.println("各众筹信息如下：");
        for (int i = 0; i < manageDOS.size(); i++) {
            ManageDO manageDO = manageDOS.get(i);
            System.out.println("===第" + (i + 1) + "位===");
            System.out.println("姓名：" + manageDO.getName());
            System.out.println("年龄：" + manageDO.getAge());
            System.out.println("性别：" + manageDO.getSex());
            System.out.println("账号：" + manageDO.getCardId());
            System.out.println("原因：" + manageDO.getReason());
            System.out.println("众筹金额：" + manageDO.getMoney());
        }
        choice(manageDOS);
    }

    //审核界面
    private void choice(ArrayList<ManageDO> manageDOS) throws SQLException, ClassNotFoundException {
        while (true) {
            System.out.println("请选择：");
            System.out.println("1、选择通过的众筹");
            System.out.println("2、返回上一页");
            int command = sc.nextInt();
            switch (command) {
                case 1:
                    System.out.println("请输入通过者的姓名：");
                    String name=sc.next();
                    for (int i = 0; i < manageDOS.size(); i++) {
                        ManageDO manageDO= manageDOS.get(i);
                        if(manageDO.getName().equals(name)){
                            //将审核通过的众筹信息加入已审核通过的数据库中
                            CrowdfundingService crowdfundingService=new CrowdfundingService();
                            crowdfundingService.addCrowdfunding(manageDO);
                            //将审核通过的众筹信息从未审核通过的数据库中删除
                            ManageService manageService=new ManageService();
                            manageService.deleteCrowdfunding(manageDO);
                            System.out.println("该众筹审核通过~");
                            break;
                        }
                    }
                    break;
                case 2:
                    return;
                default:
                    break;
            }
        }
    }
}
