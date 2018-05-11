package com.baige.action;

import com.baige.commen.Parm;
import com.baige.commen.State;
import com.baige.DAOImpl.ChatMessageDAOImpl;
import com.baige.exception.SqlException;
import com.baige.models.ChatMessage;

import java.util.List;

public class ChatMessageAction extends BaseAction {
    private int id;
    private Integer senderId;
    private Integer receiveId;
    private Long sendTime;
    private Long receiveTime;
    private String context;
    private Integer contextType;
    private Integer contextState;
    private String remark;

    private int method;
    private String senderName;
    private String receiveName;

    public String search(){
        switch (method) {
            case State.CHATMSG_METHOD_FIND_HISTORY_MSG:
                findHistoryMsg();
                break;
            case State.CHATMSG_METHOD_FIND_BY_SEND_ID:
                findMsgBySenderId();
                break;
            case State.CHATMSG_METHOD_FIND_BY_SEND_NAME:
                findMsgBySenderName();
                break;
            case State.CHATMSG_METHOD_FIND_UNREAD_BY_ID:
                findMsgByReceiveId();
                break;
            case State.CHATMSG_METHOD_FIND_UNREAD_BY_NAME:
                findMsgByReceiveName();
                break;
            case State.CHATMSG_METHOD_FIND_ALL:
                break;
            default:

        }
        return SUCCESS;
    }

    public String findHistoryMsg(){
        ChatMessageDAOImpl chatMessageDAO = new ChatMessageDAOImpl();
        try {
            List<ChatMessage> list = chatMessageDAO.findHistoryMsg(id);
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.SUCCESS_CODE);
            getResponseMsgMap().put(Parm.MEAN, "Find history Message success !");
        } catch (SqlException e) {
            e.printStackTrace();
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.FAIL_CODE);
            getResponseMsgMap().put(Parm.MEAN, "Find history Message fail !");
        }
        return SUCCESS;
    }

    public String sendMsg() {
        ChatMessage chatMessage = init();
        chatMessage.setSendTime(System.currentTimeMillis());
        chatMessage.setContextState(State.MSG_STATE_UNREAD);
        ChatMessageDAOImpl chatMessageDAO = new ChatMessageDAOImpl();
        try {
            chatMessageDAO.doSave(chatMessage);
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.SUCCESS_CODE);
            getResponseMsgMap().put(Parm.MEAN, "Send Message success !");
        } catch (SqlException e) {
            e.printStackTrace();
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.FAIL_CODE);
            getResponseMsgMap().put(Parm.MEAN, "Send Message fail !");
        }
        return SUCCESS;
    }


    public String findMsgBySenderId() {
        try {
            ChatMessageDAOImpl chatMessageDAO = new ChatMessageDAOImpl();
            chatMessageDAO.findMsgBySendId(id);
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.SUCCESS_CODE);
            getResponseMsgMap().put(Parm.MEAN, "Send Message success !");
        } catch (SqlException e) {
            e.printStackTrace();
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.FAIL_CODE);
            getResponseMsgMap().put(Parm.MEAN, "Send Message fail !");
        }
        return SUCCESS;
    }

    public String findMsgBySenderName() {
        try {
            ChatMessageDAOImpl chatMessageDAO = new ChatMessageDAOImpl();
            chatMessageDAO.findMsgBySendName(senderName);
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.SUCCESS_CODE);
            getResponseMsgMap().put(Parm.MEAN, "Send Message success !");
        } catch (SqlException e) {
            e.printStackTrace();
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.FAIL_CODE);
            getResponseMsgMap().put(Parm.MEAN, "Send Message fail !");
        }
        return SUCCESS;
    }

    public String findMsgByReceiveId() {
        try {
            ChatMessageDAOImpl chatMessageDAO = new ChatMessageDAOImpl();
            chatMessageDAO.findUnreadMsgByReceiveId(id);
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.SUCCESS_CODE);
            getResponseMsgMap().put(Parm.MEAN, "Send Message success !");
        } catch (SqlException e) {
            e.printStackTrace();
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.FAIL_CODE);
            getResponseMsgMap().put(Parm.MEAN, "Send Message fail !");
        }
        return SUCCESS;
    }

    public String findMsgByReceiveName() {
        try {
            ChatMessageDAOImpl chatMessageDAO = new ChatMessageDAOImpl();
            chatMessageDAO.findUnreadMsgByReceiveName(receiveName);
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.SUCCESS_CODE);
            getResponseMsgMap().put(Parm.MEAN, "Send Message success !");
        } catch (SqlException e) {
            e.printStackTrace();
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.FAIL_CODE);
            getResponseMsgMap().put(Parm.MEAN, "Send Message fail !");
        }
        return SUCCESS;
    }

    private ChatMessage init() {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(id);
        chatMessage.setSenderId(senderId);
        chatMessage.setReceiveId(receiveId);
        chatMessage.setSendTime(System.currentTimeMillis());
        chatMessage.setSendTime(sendTime);
        chatMessage.setReceiveTime(receiveTime);
        chatMessage.setContext(context);
        chatMessage.setContextType(contextType);
        chatMessage.setContextState(contextState);
        chatMessage.setRemark(remark);
        System.out.println(chatMessage.toString());
        return chatMessage;
    }


    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(Integer receiveId) {
        this.receiveId = receiveId;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public Long getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(Long receiveTime) {
        this.receiveTime = receiveTime;
    }

    public Integer getContextState() {
        return contextState;
    }

    public void setContextState(Integer contextState) {
        this.contextState = contextState;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }
}
