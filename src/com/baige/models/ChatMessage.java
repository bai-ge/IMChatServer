package com.baige.models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "chat_message", schema = "imchatdb", catalog = "")
public class ChatMessage {
    private int id;
    private Integer senderId;
    private Integer receiveId;
    private Long sendTime;
    private Long receiveTime;
    private String context;
    private Integer contextType;
    private Integer contextState;
    private String remark;

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", senderId=" + senderId +
                ", receiveId=" + receiveId +
                ", sendTime=" + sendTime +
                ", receiveTime=" + receiveTime +
                ", context='" + context + '\'' +
                ", contextType=" + contextType +
                ", contextState=" + contextState +
                ", remark='" + remark + '\'' +
                '}';
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "sender_id", nullable = true)
    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    @Basic
    @Column(name = "receive_id", nullable = true)
    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
    }

    @Basic
    @Column(name = "send_time", nullable = true)
    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    @Basic
    @Column(name = "receive_time", nullable = true)
    public Long getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Long receiveTime) {
        this.receiveTime = receiveTime;
    }

    @Basic
    @Column(name = "context", nullable = true, length = 1000)
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Basic
    @Column(name = "context_type", nullable = true)
    public Integer getContextType() {
        return contextType;
    }

    public void setContextType(Integer contextType) {
        this.contextType = contextType;
    }

    @Basic
    @Column(name = "context_state", nullable = true)
    public Integer getContextState() {
        return contextState;
    }

    public void setContextState(Integer contextState) {
        this.contextState = contextState;
    }

    @Basic
    @Column(name = "remark", nullable = true, length = 255)
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
                Objects.equals(senderId, that.senderId) &&
                Objects.equals(receiveId, that.receiveId) &&
                Objects.equals(sendTime, that.sendTime) &&
                Objects.equals(receiveTime, that.receiveTime) &&
                Objects.equals(context, that.context) &&
                Objects.equals(contextType, that.contextType) &&
                Objects.equals(contextState, that.contextState) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, senderId, receiveId, sendTime, receiveTime, context, contextType, contextState, remark);
    }
}
