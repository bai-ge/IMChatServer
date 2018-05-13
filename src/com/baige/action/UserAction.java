package com.baige.action;

import com.baige.commen.Parm;
import com.baige.models.User;
import com.baige.service.HibernateUitlService;
import com.baige.util.Tools;
import org.apache.struts2.ServletActionContext;

import java.io.*;

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

    private User user;

    //封装上传文件域的成员变量
    private File headImg;

    //封装上传文件类型的成员变量
    private String headImgContentType;

    //封装上传文件名的属性
    private String headImgFileName;

    private String savePath;

    private int friendId;

    //下载头像
    private String imgPath;

    private String imgFileName;

    private String keyword;



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
     *  id
     *  verification
     *  file
     * @return
     */
    public String changeImg(){
        if(getHeadImg() != null && !Tools.isEmpty(getVerification())){
            //验证用户
            User user = HibernateUitlService.checkUser(getId(), getVerification());
            if(user != null){
                try{
                    File filePath = new File(getSavePath());
                    if(!filePath.exists()){
                        filePath.mkdirs();//若文件不存在，则创建
                    }

                    FileOutputStream fos= new FileOutputStream(new File(filePath,getHeadImgFileName()));
                    FileInputStream fis=new FileInputStream(getHeadImg());
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    while( (len = fis.read(buffer) ) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                    fis.close();

                    //删除原来的头像文件
                    if(!Tools.isEmpty(user.getImgName()) && !Tools.isEquals(user.getImgName(), getHeadImgFileName())){
                        File file = new File(getSavePath(), user.getImgName());
                        if(file.exists()){
                            file.delete();
                        }
                    }

                    //更新用户头像文件名
                   if(HibernateUitlService.updateHeadImgName(getId(), getHeadImgFileName())){
                       getResponseMsgMap().clear();
                       getResponseMsgMap().put(Parm.CODE, Parm.SUCCESS_CODE);
                       getResponseMsgMap().put(Parm.MEAN, "更改头像成功");
                   }else{
                       getResponseMsgMap().clear();
                       getResponseMsgMap().put(Parm.CODE, Parm.UNKNOWN_CODE);
                       getResponseMsgMap().put(Parm.MEAN, "未知错误");
                   }
                }catch (IOException e){
                    e.printStackTrace();
                }
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


   public String downloadImg(){

        System.out.println("filePath ="+getImgPath()+", fileName ="+getImgFileName());
        return SUCCESS;
   }

    public InputStream getImgInputStream() throws Exception{
        //获得路径和文件名
        String file = getImgPath() + File.separator + getImgFileName();
        System.out.println("downloadFile ="+file);
//        return ServletActionContext.getServletContext().getResourceAsStream(file);
        InputStream is = new FileInputStream(file);
        return is;
    }




    /**通过关键词查找用户
     *  id
     *  verification
     *  keyword
     * @return
     */
    public String searchUserByKeyword(){
        if(!Tools.isEmpty(getKeyword()) && !Tools.isEmpty(getVerification())){
            //验证用户
            User user = HibernateUitlService.checkUser(getId(), getVerification());
            if(user != null){
                getResponseMsgMap().clear();
                HibernateUitlService.searchUserBykeyword(getKeyword(), getResponseMsgMap());
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

    //TODO
    /*
    *
    * */
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

    public File getHeadImg() {
        return headImg;
    }

    public void setHeadImg(File headImg) {
        this.headImg = headImg;
    }

    public String getHeadImgContentType() {
        return headImgContentType;
    }

    public void setHeadImgContentType(String headImgContentType) {
        this.headImgContentType = headImgContentType;
    }

    public String getHeadImgFileName() {
        return headImgFileName;
    }

    public void setHeadImgFileName(String headImgFileName) {
        this.headImgFileName = headImgFileName;
    }

    public String getSavePath(){
        return ServletActionContext.getServletContext()
                .getRealPath(savePath);
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getImgPath() {
        return ServletActionContext.getServletContext()
                .getRealPath(imgPath);
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
        if(imgFileName != null){
            try {
                this.imgFileName = new String(imgFileName.getBytes(), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
