package com.baige.action;


import com.baige.commen.Parm;

import java.io.File;

public class FileAction extends BaseAction {
    private File img;
    private String imgFileName;
    private String imgContentType;

    public String upload(){
        System.out.println("file ="+img + ", fileName =" + imgFileName + ", type ="+imgContentType);
        getResponseMsgMap().clear();
        getResponseMsgMap().put(Parm.CODE, Parm.SUCCESS_CODE);
        getResponseMsgMap().put(Parm.FILE, getImg());
        getResponseMsgMap().put(Parm.FILE_NAME, getImgFileName());
        getResponseMsgMap().put(Parm.CONTENT_TYPE, getImgContentType());
        return SUCCESS;
    }

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    public String getImgContentType() {
        return imgContentType;
    }

    public void setImgContentType(String imgContentType) {
        this.imgContentType = imgContentType;
    }
}
