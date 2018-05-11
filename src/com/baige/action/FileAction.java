package com.baige.action;



import java.io.File;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import org.apache.struts2.ServletActionContext;


public class FileAction  extends BaseAction{
    //封装上传文件域的成员变量
    private File upload;

    //封装上传文件类型的成员变量
    private String uploadContentType;

    //封装上传文件名的属性
    private String uploadFileName;

    private String savePath;

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

        FileOutputStream fos= new FileOutputStream(new File(f,getUploadFileName()));
        FileInputStream fis=new FileInputStream(getUpload());
        byte[] buffer=new byte[1024];
        int len=0;
        while((len=fis.read(buffer))>0)
            fos.write(buffer,0,len);
        fos.close();
        fis.close();
        return SUCCESS;
    }

}
