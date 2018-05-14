package com.baige.models;

import java.util.Objects;

public class ChatMessage {
    private int id;
    private int senderId;
    private int receiveId;
    private Long sendTime;
    private String context;
    private Integer contextType;
    private Integer contextState;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(int receiveId) {
        this.receiveId = receiveId;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Integer getContextType() {
        return contextType;
    }

    public void setContextType(Integer contextType) {
        this.contextType = contextType;
    }

    public Integer getContextState() {
        return contextState;
    }

    public void setContextState(Integer contextState) {
        this.contextState = contextState;
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
        ChatMessage that = (ChatMessage) o;
        return id == that.id &&
                senderId == that.senderId &&
                receiveId == that.receiveId &&
                Objects.equals(sendTime, that.sendTime) &&
                Objects.equals(context, that.context) &&
                Objects.equals(contextType, that.contextType) &&
                Objects.equals(contextState, that.contextState) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiveId=" + receiveId +
                ", sendTime=" + sendTime +
                ", context='" + context + '\'' +
                ", contextType=" + contextType +
                ", contextState=" + contextState +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, senderId, receiveId, sendTime, context, contextType, contextState, remark);
    }
}
