package com.baige.models;

import java.util.Objects;

public class Friend {
    private int id;
    private Integer userId;
    private Integer friendId;
    private String userAlias;
    private String friendAlias;
    private Long relateTime;
    private Integer state;
    private Integer userReadState;
    private Integer friendReadState;
    private String remark;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFriendId() {
        return friendId;
    }

    public void setFriendId(Integer friendId) {
        this.friendId = friendId;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public void setUserAlias(String userAlias) {
        this.userAlias = userAlias;
    }

    public String getFriendAlias() {
        return friendAlias;
    }

    public void setFriendAlias(String friendAlias) {
        this.friendAlias = friendAlias;
    }

    public Long getRelateTime() {
        return relateTime;
    }

    public void setRelateTime(Long relateTime) {
        this.relateTime = relateTime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getUserReadState() {
        return userReadState;
    }

    public void setUserReadState(Integer userReadState) {
        this.userReadState = userReadState;
    }

    public Integer getFriendReadState() {
        return friendReadState;
    }

    public void setFriendReadState(Integer friendReadState) {
        this.friendReadState = friendReadState;
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
        Friend friend = (Friend) o;
        return id == friend.id &&
                Objects.equals(userId, friend.userId) &&
                Objects.equals(friendId, friend.friendId) &&
                Objects.equals(userAlias, friend.userAlias) &&
                Objects.equals(friendAlias, friend.friendAlias) &&
                Objects.equals(relateTime, friend.relateTime) &&
                Objects.equals(state, friend.state) &&
                Objects.equals(userReadState, friend.userReadState) &&
                Objects.equals(friendReadState, friend.friendReadState) &&
                Objects.equals(remark, friend.remark);
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id=" + id +
                ", userId=" + userId +
                ", friendId=" + friendId +
                ", userAlias='" + userAlias + '\'' +
                ", friendAlias='" + friendAlias + '\'' +
                ", relateTime=" + relateTime +
                ", state=" + state +
                ", userReadState=" + userReadState +
                ", friendReadState=" + friendReadState +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, friendId, userAlias, friendAlias, relateTime, state, userReadState, friendReadState, remark);
    }
}
