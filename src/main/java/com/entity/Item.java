package com.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    private int iid;
    private String uid;
    private int pid;
    private BigDecimal count;
    private int num;
    private String oid;
    private int inum;
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getInum() {
        return inum;
    }

    public void setInum(int inum) {
        this.inum = inum;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        this.iid = iid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Item{" +
                "iid=" + iid +
                ", uid='" + uid + '\'' +
                ", pid=" + pid +
                ", count=" + count +
                ", num=" + num +
                ", oid='" + oid + '\'' +
                ", inum=" + inum +
                '}';
    }
}
