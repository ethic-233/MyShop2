package com.entity;

import java.io.Serializable;

public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    private int tid;
    private String name;
    private String info;

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Type{" +
                "tid=" + tid +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
