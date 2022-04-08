package com.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    private int cid;
    private int uid;
    private int pid;
    private Product product;
    private int cnum = 0; // 购物车商品数量
    private BigDecimal ccount; // 购物车小计

    public Cart() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCnum() {
        return cnum;
    }

    public void setCnum(int cnum) {
        this.cnum = cnum;
    }

    public BigDecimal getCcount() {
        BigDecimal pprice = product.getPprice();
        BigDecimal bigDecimal = new BigDecimal(cnum);
        
        return pprice.multiply(bigDecimal);
    }

    public void setCcount(BigDecimal ccount) {
        this.ccount = ccount;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cid=" + cid +
                ", uid=" + uid +
                ", pid=" + pid +
                ", product=" + product +
                ", cnum=" + cnum +
                ", ccount=" + ccount +
                '}';
    }
}
