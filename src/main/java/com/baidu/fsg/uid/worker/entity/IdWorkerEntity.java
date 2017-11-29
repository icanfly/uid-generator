package com.baidu.fsg.uid.worker.entity;

import java.util.Date;

/**
 * @author luopeng
 * created at 2017/11/27
 */
public class IdWorkerEntity {

    private Long workerId;

    private String host;

    private String hostMac;

    private Date launchTime;

    private Date heartbeatTime;

    private Date createTime;

    private Date updateTime;

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHostMac() {
        return hostMac;
    }

    public void setHostMac(String hostMac) {
        this.hostMac = hostMac;
    }

    public Date getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(Date launchTime) {
        this.launchTime = launchTime;
    }

    public Date getHeartbeatTime() {
        return heartbeatTime;
    }

    public void setHeartbeatTime(Date heartbeatTime) {
        this.heartbeatTime = heartbeatTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IdWorkerEntity{");
        sb.append("workerId=").append(workerId);
        sb.append(", host='").append(host).append('\'');
        sb.append(", hostMac='").append(hostMac).append('\'');
        sb.append(", launchTime=").append(launchTime);
        sb.append(", heartbeatTime=").append(heartbeatTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append('}');
        return sb.toString();
    }
}
