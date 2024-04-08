package com.czy.zhongchou.entity;

import java.sql.Timestamp;

public class CrowdfundingDO {
    private String cardId;
    private String name;
    private int age;
    private String sex;
    private String reason;
    private double money;
    private int number;
    private Timestamp time;

    public CrowdfundingDO() {
    }

    public CrowdfundingDO(String cardId, String name, int age, String sex, String reason, double money, int number, Timestamp time) {
        this.cardId = cardId;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.reason = reason;
        this.money = money;
        this.number = number;
        this.time = time;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
