package com.zsx.springbootderby.entity;

import java.io.Serializable;

/**
 * @author zhaoshuxue3
 * @Date 2019/7/30 15:31
 **/
public class User implements Serializable {

    private int id;
    private String name;
    private String pwd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
