package com.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private int pid;
    private int tid;
    private String name;
    private Date time;
    private String image;
    private int state;
    private BigDecimal pprice;
    private String info;

    public BigDecimal getPprice() {
        return pprice;
    }

    public void setPprice(BigDecimal pprice) {
        this.pprice = pprice;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", tid=" + tid +
                ", name='" + name + '\'' +
                ", time=" + time +
                ", image='" + image + '\'' +
                ", state=" + state +
                ", info='" + info + '\'' +
                '}';
    }
}
