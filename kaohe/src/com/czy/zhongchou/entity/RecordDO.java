package com.czy.zhongchou.entity;


import java.sql.Timestamp;

public class RecordDO {
    private String donor;
    private String recipient;
    private double money;
    private Timestamp timestamp;

    public RecordDO() {
    }

    public RecordDO(String donor, String recipient, double money, Timestamp timestamp) {
        this.donor = donor;
        this.recipient = recipient;
        this.money = money;
        this.timestamp = timestamp;
    }

    public String getDonor() {
        return donor;
    }

    public void setDonor(String donor) {
        this.donor = donor;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
