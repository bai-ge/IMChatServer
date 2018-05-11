package com.baige.models;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String password;
    private String alias;
    private String deviceId;
    private Long loginTime;
    private String loginIp;
    private Long registerTime;
    private String verification;
    private String imgName;
    private String remark;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(alias, user.alias) &&
                Objects.equals(deviceId, user.deviceId) &&
                Objects.equals(loginTime, user.loginTime) &&
                Objects.equals(loginIp, user.loginIp) &&
                Objects.equals(registerTime, user.registerTime) &&
                Objects.equals(verification, user.verification) &&
                Objects.equals(imgName, user.imgName) &&
                Objects.equals(remark, user.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, password, alias, deviceId, loginTime, loginIp, registerTime, verification, imgName, remark);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", alias='" + alias + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", loginTime=" + loginTime +
                ", loginIp='" + loginIp + '\'' +
                ", registerTime=" + registerTime +
                ", verification='" + verification + '\'' +
                ", imgName='" + imgName + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
