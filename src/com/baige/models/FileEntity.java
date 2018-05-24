package com.baige.models;

import java.util.Objects;

public class FileEntity {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileEntity that = (FileEntity) o;
        return id == that.id &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(fileName, that.fileName) &&
                Objects.equals(filePath, that.filePath) &&
                Objects.equals(fileType, that.fileType) &&
                Objects.equals(fileSize, that.fileSize) &&
                Objects.equals(fileDescribe, that.fileDescribe) &&
                Objects.equals(uploadTime, that.uploadTime) &&
                Objects.equals(downloadCount, that.downloadCount) &&
                Objects.equals(remark, that.remark);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, fileName, filePath, fileType, fileSize, fileDescribe, uploadTime, downloadCount, remark);
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "id=" + id +
                ", userId=" + userId +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", fileType=" + fileType +
                ", fileSize=" + fileSize +
                ", fileDescribe='" + fileDescribe + '\'' +
                ", uploadTime=" + uploadTime +
                ", downloadCount=" + downloadCount +
                ", remark='" + remark + '\'' +
                '}';
    }
}
