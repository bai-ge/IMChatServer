package com.baige.action;

import com.baige.commen.Parm;
import com.baige.models.Friend;
import com.baige.models.User;
import com.baige.service.HibernateUitlService;
import com.baige.util.Tools;


public class FriendAction extends BaseAction {

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

    private Friend friend;

    private int uid;
    private String verification;

    private String alias;

    private String operation;


    /**
     * uid
     * verification
     * @return
     */
    public String searchFriends(){
        if(!Tools.isEmpty(verification)){
            User user = HibernateUitlService.checkUser(getUid(), getVerification());
            if(user != null){
                getResponseMsgMap().clear();
                 HibernateUitlService.searchFriends(uid, getResponseMsgMap());
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
     * id
     * uid
     * verification
     * alias
     * @return
     */
    public String changFriendAlias(){
        if(!Tools.isEmpty(verification) && !Tools.isEmpty(getAlias())){
            User user = HibernateUitlService.checkUser(getUid(), getVerification());
            if(user != null){
                getResponseMsgMap().clear();
                HibernateUitlService.changFriendAlias(id, uid, getAlias(), getResponseMsgMap());
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
     * uid
     * verification
     * friendId
     * @return
     */
    public String relateUser(){
        if(!Tools.isEmpty(verification) ){
            User user = HibernateUitlService.checkUser(getUid(), getVerification());
            if(user != null){
                getResponseMsgMap().clear();
                HibernateUitlService.relateUser(uid, friendId, getResponseMsgMap());
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
     * id
     * uid
     * verification
     * friendId
     * operation
     * @return
     */
    public String operation(){
        if(!Tools.isEmpty(verification) && !Tools.isEmpty(operation)){
            User user = HibernateUitlService.checkUser(getUid(), getVerification());
            if(user != null){
                getResponseMsgMap().clear();
                switch (operation){
                    case "agree":
                        HibernateUitlService.agreeFriend(getId(), getUid(), getFriendId(), getResponseMsgMap());
                        break;
                    case "reject":
                        HibernateUitlService.rejectFriend(getId(), getUid(), getFriendId(), getResponseMsgMap());
                        break;
                    case "delete":
                        HibernateUitlService.deleteFriend(getId(), getUid(), getFriendId(), getResponseMsgMap());
                        break;
                    case "defriend":
                        HibernateUitlService.deFriend(getId(), getUid(), getFriendId(), getResponseMsgMap());
                        break;
                }
                HibernateUitlService.relateUser(uid, friendId, getResponseMsgMap());
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


    /*get and set*/

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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
