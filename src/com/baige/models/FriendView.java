package com.baige.models;

public class FriendView {
    private int id; //Friend 表中的ID
    private int uid; //用户ID
    private String friendName;
    private int friendId;
    private String alias;
    private String friendAlias;
    private long relateTime;
    private int state;
    private int readState; //自己是否已读
    private String remake;
    private String friendImgName;
    private String friendDeviceId;

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getFriendAlias() {
        return friendAlias;
    }

    public void setFriendAlias(String friendAlias) {
        this.friendAlias = friendAlias;
    }

    public long getRelateTime() {
        return relateTime;
    }

    public void setRelateTime(long relateTime) {
        this.relateTime = relateTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getReadState() {
        return readState;
    }

    public void setReadState(int readState) {
        this.readState = readState;
    }

    public String getRemake() {
        return remake;
    }

    public void setRemake(String remake) {
        this.remake = remake;
    }

    public String getFriendImgName() {
        return friendImgName;
    }

    public void setFriendImgName(String friendImgName) {
        this.friendImgName = friendImgName;
    }

    public String getFriendDeviceId() {
        return friendDeviceId;
    }

    public void setFriendDeviceId(String friendDeviceId) {
        this.friendDeviceId = friendDeviceId;
    }
}
