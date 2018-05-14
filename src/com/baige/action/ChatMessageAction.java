package com.baige.action;

import com.baige.commen.Parm;

import com.baige.models.ChatMessage;
import com.baige.models.User;
import com.baige.service.HibernateUitlService;
import com.baige.util.Tools;


public class ChatMessageAction extends BaseAction {
    private int id;
    private Integer senderId;
    private Integer receiveId;
    private Long sendTime;
    private String context;
    private Integer contextType;
    private Integer contextState;
    private String remark;

    private String senderName;
    private String receiveName;

    private String verification;
    /**
     * senderId
     * receiveId
     * verification
     * context
     * contextType 可有可无
     * @return
     */
    public String sendMsg() {
        if(!Tools.isEmpty(getVerification()) && !Tools.isEmpty(context) && senderId != 0 && receiveId != 0){
            User user = HibernateUitlService.checkUser(senderId, getVerification());
            if(user != null){
                ChatMessage chatMessage = init();
                getResponseMsgMap().clear();
                HibernateUitlService.sendMessage(chatMessage, getResponseMsgMap());
            }else{
                getResponseMsgMap().clear();
                getResponseMsgMap().put(Parm.CODE, Parm.INVALID_CODE);
                getResponseMsgMap().put(Parm.MEAN, "验证失败");
            }
        }else{
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.UNKNOWN_CODE);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }

    /**
     * senderId
     * receiveId
     * verification
     * @return
     */
    public String findMsgRelate() {
        if(!Tools.isEmpty(getVerification()) && senderId != null && receiveId != null){
            User user = HibernateUitlService.checkUser(senderId, getVerification());
            if(user != null){
                getResponseMsgMap().clear();
                HibernateUitlService.findMsgRelate(senderId, receiveId, getResponseMsgMap());
            }else{
                getResponseMsgMap().clear();
                getResponseMsgMap().put(Parm.CODE, Parm.INVALID_CODE);
                getResponseMsgMap().put(Parm.MEAN, "验证失败");
            }
        }else{
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.UNKNOWN_CODE);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }

    /**
     * senderId
     * receiveId
     * verification
     * sendTime
     * @return
     */
    public String findMsgRelateAfterTime() {
        if(!Tools.isEmpty(getVerification()) && senderId != null && receiveId != null){
            User user = HibernateUitlService.checkUser(senderId, getVerification());
            if(user != null){
                getResponseMsgMap().clear();
                HibernateUitlService.findMsgRelateAfterTime(senderId, receiveId, sendTime, getResponseMsgMap());
            }else{
                getResponseMsgMap().clear();
                getResponseMsgMap().put(Parm.CODE, Parm.INVALID_CODE);
                getResponseMsgMap().put(Parm.MEAN, "验证失败");
            }
        }else{
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.UNKNOWN_CODE);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }

    /**
     * senderId
     * receiveId
     * verification
     * sendTime
     * @return
     */
    public String findMsgRelateBeforeTime() {
        if(!Tools.isEmpty(getVerification()) && senderId != null && receiveId != null){
            User user = HibernateUitlService.checkUser(senderId, getVerification());
            if(user != null){
                getResponseMsgMap().clear();
                HibernateUitlService.findMsgRelateBeforeTime(senderId, receiveId, sendTime, getResponseMsgMap());
            }else{
                getResponseMsgMap().clear();
                getResponseMsgMap().put(Parm.CODE, Parm.INVALID_CODE);
                getResponseMsgMap().put(Parm.MEAN, "验证失败");
            }
        }else{
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.UNKNOWN_CODE);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }




    private ChatMessage init() {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setId(id);
        chatMessage.setSenderId(senderId);
        chatMessage.setReceiveId(receiveId);
        chatMessage.setSendTime(System.currentTimeMillis());
        if(getSendTime() != null){
            chatMessage.setSendTime(getSendTime());
        }
        chatMessage.setContext(context);
        chatMessage.setContextType(Parm.MSG_TYPE_TEXT);
        if(contextType != null){
            chatMessage.setContextType(contextType);
        }
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

    public Integer getContextState() {
        return contextState;
    }

    public void setContextState(Integer contextState) {
        this.contextState = contextState;
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

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }
}
