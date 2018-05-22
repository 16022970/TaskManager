package com.example.a16022970.taskmanager;

import java.io.Serializable;

public class Task implements Serializable {

    private int id;
    private String name;
    private String desc;
    private String sec;

    public Task(int id, String name, String desc, String sec) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.sec = sec;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }
}
