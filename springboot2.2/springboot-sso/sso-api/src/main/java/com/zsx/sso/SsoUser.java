package com.zsx.sso;

import java.io.Serializable;

public class SsoUser implements Serializable {

    private Integer userId;

    private String username;

    private String uuid;



//  getter  setter


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
