package com.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    private String oid;
    private int uid;
    private BigDecimal count;
    private Date time;
    private int state;
    private int aid;
    private Date otime;
    private BigDecimal ocount;
    private int ostate;
    private Address address;
    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getOstate() {
        return ostate;
    }

    public void setOstate(int ostate) {
        this.ostate = ostate;
    }

    public BigDecimal getOcount() {
        return ocount;
    }

    public void setOcount(BigDecimal ocount) {
        this.ocount = ocount;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public Date getOtime() {
        return otime;
    }

    public void setOtime(Date otime) {
        this.otime = otime;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid='" + oid + '\'' +
                ", uid=" + uid +
                ", count=" + count +
                ", time=" + time +
                ", state=" + state +
                ", aid=" + aid +
                ", otime=" + otime +
                ", ocount=" + ocount +
                ", ostate=" + ostate +
                '}';
    }
}
