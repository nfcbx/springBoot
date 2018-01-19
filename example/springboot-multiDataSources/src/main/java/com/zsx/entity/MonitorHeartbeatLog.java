package com.zsx.entity;

import java.util.Date;

public class MonitorHeartbeatLog {
    private Integer id;

    private Integer configId;

    private Integer intervalTime;

    private String totalJvm;

    private String usedJvm;

    private String cpu;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }

    public Integer getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(Integer intervalTime) {
        this.intervalTime = intervalTime;
    }

    public String getTotalJvm() {
        return totalJvm;
    }

    public void setTotalJvm(String totalJvm) {
        this.totalJvm = totalJvm == null ? null : totalJvm.trim();
    }

    public String getUsedJvm() {
        return usedJvm;
    }

    public void setUsedJvm(String usedJvm) {
        this.usedJvm = usedJvm == null ? null : usedJvm.trim();
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu == null ? null : cpu.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}