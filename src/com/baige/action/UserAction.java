package com.baige.action;

import com.baige.commen.Parm;
import com.baige.models.User;
import com.baige.service.HibernateUitlService;
import com.baige.util.Tools;
import org.apache.struts2.ServletActionContext;

/**
 *
 */
public class UserAction extends BaseAction {

    private int id;
    private String name;
    private String password;
    private String alias;
    private String deviceId;
    private Long loginTime;
    private String loginIp;
    private Long registerTime;
    private String verification; // 登录成功之后服务器返回一个验证码，后期操作都要根据这个字段判断用户
    private String imgName;
    private String remark;


    private int friendId;

    private User user;

    private void init(){
        if(user == null){
            user = new User();
        }
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setAlias(alias);
        user.setDeviceId(deviceId);
        user.setLoginTime(loginTime);
        user.setLoginIp(loginIp);
        user.setRegisterTime(registerTime);
        user.setVerification(verification);
        user.setImgName(imgName);
        user.setRemark(remark);
    }

    /**登录
     * {
     * 	"name":"baige",
     * 	"password":"123456",
     * 	"deviceId":"jfeigiejidjks"
     * }
     * @return
     */
    public String login(){
        if(!Tools.isEmpty(getName()) &&
                !Tools.isEmpty(getPassword())&&
                !Tools.isEmpty(getDeviceId())) {
            init();
            user.setLoginTime(System.currentTimeMillis());
            user.setLoginIp(ServletActionContext.getRequest().getRemoteAddr());
            getResponseMsgMap().clear();
            HibernateUitlService.login(user, getResponseMsgMap());
        }else{
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.UNKNOWN_CODE);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }

    /**
     *由客户端主动删除
     * verification 即可
     * @return
     */
    public String logout(){
        return SUCCESS;
    }

    /**注册
     * {
     * 	"name":"Ant",
     * 	"password":"123456"
     * }
     * @return
     */
    public String register(){
        if(!Tools.isEmpty(getName()) &&
                !Tools.isEmpty(getPassword())) {
            init();
            user.setRegisterTime(System.currentTimeMillis());
            getResponseMsgMap().clear();
            HibernateUitlService.register(user, getResponseMsgMap());
        }else{
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.UNKNOWN_CODE);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }

    /**修改别名
     *{
     * "id":1,
     * "alias":"jigjei",
     * "verification":"1525878806203_2661"
     * }
     * @return
     */
    public String alias(){
        if(!Tools.isEmpty(getAlias()) &&
                !Tools.isEmpty(getVerification())) {
            init();
            getResponseMsgMap().clear();
            HibernateUitlService.updateAlias(user, getResponseMsgMap());
        }else{
            getResponseMsgMap().clear();
            getResponseMsgMap().put(Parm.CODE, Parm.UNKNOWN_CODE);
            getResponseMsgMap().put(Parm.MEAN, "参数错误");
        }
        return SUCCESS;
    }


    /**修改头像
     *
     * @return
     */
    public String uploadImg(){
        return SUCCESS;
    }




    //TODO
    public String findAllFriends(){
        return SUCCESS;
    }

    public String addFriend(){
        return SUCCESS;
    }

    public String deleteFriend(){
        return SUCCESS;
    }

    public String seachFriend(){
        return SUCCESS;
    }


    /*get and set*/

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

    public int getFriendId() {
        return friendId;
    }

    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }
}
