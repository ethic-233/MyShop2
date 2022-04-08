package com.entity;

import java.io.Serializable;

public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    private int aid;
    private int uid;
    private String name; // 收件人名称
    private String phone; // 收件人电话
    private String detail; // 收件人地址
    private int state; // 收件地址状态0 非默认1

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "aid=" + aid +
                ", uid=" + uid +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", detail='" + detail + '\'' +
                ", state=" + state +
                '}';
    }
}
