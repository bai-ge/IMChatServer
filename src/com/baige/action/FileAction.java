package com.baige.action;



import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.baige.models.FileEntity;
import com.baige.service.HibernateUitlService;
import com.baige.util.Tools;
import org.apache.struts2.ServletActionContext;


public class FileAction  extends BaseAction{


    private int id;
    private Integer userId;
    private String fileName;
    private String filePath;
    private Integer fileType;
    private Long fileSize;
    private String fileDescribe;
    private Long uploadTime;
    private Integer downloadCount;
    private String remark;


    //封装上传文件域的成员变量
    private File upload;

    //封装上传文件类型的成员变量
    private String uploadContentType;

    //封装上传文件名的属性
    private String uploadFileName;

    private String savePath;

    private int uid;

    private String verification;


    private FileEntity init(){
        FileEntity fileEntity = new FileEntity();
        fileEntity.setUserId(getUid());
        fileEntity.setFileName(fileName);
        fileEntity.setFilePath(filePath);
        fileEntity.setFileType(fileType);
        fileEntity.setFileSize(fileSize);
        fileEntity.setFileDescribe(fileDescribe);
        fileEntity.setUploadTime(System.currentTimeMillis());
        fileEntity.setDownloadCount(downloadCount);
        fileEntity.setRemark(remark);
        return fileEntity;
    }

    public void setSavePath(String value){
        this.savePath=value;
    }

    public String getSavePath(){
        return ServletActionContext.getServletContext()
                .getRealPath(savePath);
    }

    public void setUpload(File upload){
        this.upload=upload;
    }

    public File getUpload(){
        return this.upload;
    }

    public void setUploadContentType(String uploadContentType){
        this.uploadContentType=uploadContentType;
    }
    public String getUploadContentType(){
        return this.uploadContentType;
    }
    public void setUploadFileName(String uploadFileName){
        this.uploadFileName=uploadFileName;
    }
    public String getUploadFileName(){
        return this.uploadFileName;
    }

    public String uploadFile()throws Exception{
        System.out.println("保存路径："+getSavePath());
        System.out.println("原始路径："+getUpload());
        if(getUpload() != null){
            System.out.println(getUpload().getAbsolutePath());
        }


        File f=new File(getSavePath());
        if(!f.exists()){
            f.mkdirs();//若文件不存在，则创建
        }


        File saveFile = new File(f, getUpload().getName());
        FileOutputStream fos= new FileOutputStream(saveFile);
        FileInputStream fis=new FileInputStream(getUpload());
        byte[] buffer=new byte[1024];
        int len=0;
        while((len=fis.read(buffer))>0){
            fos.write(buffer,0,len);
        }

        fos.close();
        fis.close();

        //保存进数据库
        FileEntity entity = init();
        entity.setUserId(getUid());
        entity.setFileName(getUploadFileName());
        entity.setFilePath(saveFile.getAbsolutePath());
        System.out.println(entity.toString());
        getResponseMsgMap().clear();
        HibernateUitlService.saveFile(entity, getResponseMsgMap());
        return SUCCESS;
    }


    /**
     * @return 查找所有文件
     * @throws Exception
     */
    public String search() throws Exception {
        getResponseMsgMap().clear();
        HibernateUitlService.searchAllFile(getResponseMsgMap());
        return SUCCESS;
    }

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileDescribe() {
        return fileDescribe;
    }

    public void setFileDescribe(String fileDescribe) {
        this.fileDescribe = fileDescribe;
    }

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
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


}
