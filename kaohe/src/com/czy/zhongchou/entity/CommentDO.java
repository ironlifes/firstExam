package com.czy.zhongchou.entity;

public class CommentDO {
    private int commentNumber;
    private int crowdfundingNumber;
    private String name;
    private String comment;

    public CommentDO() {
    }

    public CommentDO(int commentNumber, int crowdfundingNumber, String name, String comment) {
        this.commentNumber = commentNumber;
        this.crowdfundingNumber = crowdfundingNumber;
        this.name = name;
        this.comment = comment;
    }

    public int getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(int commentNumber) {
        this.commentNumber = commentNumber;
    }

    public int getCrowdfundingNumber() {
        return crowdfundingNumber;
    }

    public void setCrowdfundingNumber(int crowdfundingNumber) {
        this.crowdfundingNumber = crowdfundingNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
